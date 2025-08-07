package com.demo.interview.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Document")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionRequest {

	@XmlElement(name = "Transaction")
    private Transaction transaction;
	
    public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Transaction {

        @XmlElement(name = "Dbtr")
        private Party debtor;

        @XmlElement(name = "Cdtr")
        private Party creditor;

        @XmlElement(name = "Amt")
        private double amount;

		public Party getDebtor() {
			return debtor;
		}
		public void setDebtor(Party debtor) {
			this.debtor = debtor;
		}
		public Party getCreditor() {
			return creditor;
		}
		public void setCreditor(Party creditor) {
			this.creditor = creditor;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Party {
        @XmlElement(name = "Nm")
        private String name;

        @XmlElement(name = "Acct")
        private Account account;

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Account getAccount() {
			return account;
		}
		public void setAccount(Account account) {
			this.account = account;
		}

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Account {
        @XmlElement(name = "Id")
        private Long id;

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}

    }
    
    @XmlElement(name = "PaymentType")
    private String paymentType;

    public String getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}

