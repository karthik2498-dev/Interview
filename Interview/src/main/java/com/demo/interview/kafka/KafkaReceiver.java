package com.demo.interview.kafka;

import java.io.StringReader;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.demo.interview.dto.TransactionRequest;
import com.demo.interview.service.TransactionService;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

@Service
public class KafkaReceiver {
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private TransactionService service;

    @KafkaListener(topics = "payments.inward", groupId = "payment-router")
    public void receiveFromKafka(ConsumerRecord<String, String> record) {
        String xmlMessage = record.value();
        try {
        	 System.err.println("Message Received : "+xmlMessage);
            // Convert XML string to TransactionRequest object
            TransactionRequest request = convertXmlToObject(xmlMessage);
            String paymentType = request.getTransaction().getPaymentType();
            
            if(paymentType.equalsIgnoreCase("inward")) {
            	System.out.println("Initiating Transaction");
            	service.processTransaction(request);
            }
            else {
            	System.out.println("Failed");	
            }
            
        } catch (Exception e) {
            System.err.println("Failed to process message:");
            e.printStackTrace();
        }
    }

    private TransactionRequest convertXmlToObject(String xml) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(TransactionRequest.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (TransactionRequest) unmarshaller.unmarshal(new StringReader(xml));
    }
}

