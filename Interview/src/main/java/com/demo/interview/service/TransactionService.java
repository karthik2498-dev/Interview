package com.demo.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.interview.dto.TransactionRequest;
import com.demo.interview.entity.UserAccount;
import com.demo.interview.repository.UserAccountRepo;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

    @Autowired
    private UserAccountRepo userRepo;

    @Transactional
    public String processTransaction(TransactionRequest request) {
        Long dbtrAcct = request.getTransaction().getDebtor().getAccount().getId();
        Long cdtrAcct = request.getTransaction().getCreditor().getAccount().getId();
        double amt = request.getTransaction().getAmount();

        UserAccount debtor = userRepo.findByAccountNum(dbtrAcct)
            .orElseThrow(() -> new RuntimeException("Debtor account not found"));

        UserAccount creditor = userRepo.findByAccountNum(cdtrAcct)
            .orElseThrow(() -> new RuntimeException("Creditor account not found"));

        if (debtor.getBalance() < amt) {
            throw new RuntimeException("Insufficient balance");
        }

        debtor.setBalance(debtor.getBalance() - amt);
        creditor.setBalance(creditor.getBalance() + amt);

       // userRepo.save(debtor);
        //userRepo.save(creditor);
        System.err.println("Transaction successful");
        return "Transaction successful";
    }
}

