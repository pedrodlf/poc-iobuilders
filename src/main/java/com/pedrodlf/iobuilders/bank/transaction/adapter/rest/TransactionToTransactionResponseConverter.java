package com.pedrodlf.iobuilders.bank.transaction.adapter.rest;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pedrodlf.iobuilders.bank.transaction.Transaction;
import com.pedrodlf.iobuilders.model.TransactionResponse;

@Component
public class TransactionToTransactionResponseConverter implements Converter<Transaction, TransactionResponse> {

    public static final String URI = "/transactions/";

    @Override
    public TransactionResponse convert(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionID(transaction.getTransactionID());
        response.setBeneficiaryIBAN(transaction.getBeneficiaryIBAN());
        response.setIssuerIBAN(transaction.getIssuerIBAN());
        response.setAmount(transaction.getAmount());
        response.setUri(URI+transaction.getTransactionID());
        response.setDate(transaction.getDate());
        return response;
    }
}
