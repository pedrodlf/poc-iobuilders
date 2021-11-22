package com.pedrodlf.iobuilders.bank.transaction.adapter.rest;

import com.pedrodlf.iobuilders.bank.transaction.TransactionService;
import com.pedrodlf.iobuilders.model.TransactionRequest;
import com.pedrodlf.iobuilders.model.TransactionResponse;
import com.pedrodlf.iobuilders.model.Transactions;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransactionAdapter {
    private TransactionService transactionService;

    public TransactionAdapter(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    public TransactionResponse createTransaction(TransactionRequest transactionRequest){
        return transactionService.createTransaction(transactionRequest);
    }

    public TransactionResponse getTransactionById(UUID transactionId){
        return transactionService.getTransactionById(transactionId);
    }

    public Transactions getTransactionsByAccount(String iban){
        return transactionService.getTransactionsByAccount(iban);
    }

}
