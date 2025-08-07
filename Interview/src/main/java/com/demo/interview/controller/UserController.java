package com.demo.interview.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.interview.dto.TransactionRequest;
import com.demo.interview.dto.UserAccountDTO;
import com.demo.interview.entity.UserAccount;
import com.demo.interview.service.TransactionService;
import com.demo.interview.service.UserAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("userservice/api")
public class UserController {

    private final UserAccountService service;
    
    private final TransactionService tservice;

    public UserController(UserAccountService service,TransactionService tservice) {
        this.service = service;
        this.tservice=tservice;
    }

    @GetMapping("/getaccount")
    public ResponseEntity<UserAccount> getAccount(@RequestParam("Accnum") Long accNum) throws SQLException {
        Optional<UserAccount> result = service.GetAccountDetailsbyID(accNum);
        return result.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/deleteaccount")
    public ResponseEntity<String> DeleteAccount(@RequestParam("Accnum") Long Accnum) {
        try {
            String result = service.DeleteAccountByAccID(Accnum);
            return ResponseEntity.ok(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAILED");
        }
    }

    @PostMapping("/Saveaccount")
    public ResponseEntity<String> SaveAccount(@RequestBody List<UserAccountDTO> useracc) {
        try {
            String result = service.SaveUserAccount(useracc);
            return ResponseEntity.ok(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAILED");
        }
    }

    @PutMapping("/updateacount")
    public ResponseEntity<String> UpdateAccount() {
        return ResponseEntity.ok("Dummy");
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE,value="/Inward Debit")
    public ResponseEntity<String> makeTransaction(@RequestBody TransactionRequest request) {
        try {
            String response = tservice.processTransaction(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PostMapping(
    	    value = "/inward-debit-json",
    	    consumes = MediaType.APPLICATION_XML_VALUE,
    	    produces = MediaType.APPLICATION_JSON_VALUE
    	)
    	@Operation(
    	    summary = "Inward Debit with JSON conversion",
    	    description = "Accepts ISO20022 XML, converts it to JSON, and performs the transaction"
    	)
    	public ResponseEntity<?> inwardDebitWithJson(@RequestBody TransactionRequest request) {
    	    try {
    	        // Optional: Print the input as JSON to console/log
    	        ObjectMapper objectMapper = new ObjectMapper();
    	        String jsonInput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
    	        System.out.println("Received XML converted to JSON:\n" + jsonInput);

    	        TransactionRequest requestFromJson = objectMapper.readValue(jsonInput, TransactionRequest.class);

    	        String result = tservice.processTransaction(requestFromJson);

    	        // Return both result and the JSON input for confirmation
    	        Map<String, Object> response = new HashMap<>();
    	        response.put("message", result);
    	        response.put("parsedJson", request);

    	        return ResponseEntity.ok(response);
    	    } catch (Exception e) {
    	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
    	    }
    	}

}

