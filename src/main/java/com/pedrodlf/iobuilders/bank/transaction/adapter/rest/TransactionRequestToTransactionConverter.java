package com.pedrodlf.iobuilders.bank.transaction.adapter.rest;

import java.util.Date;
import java.util.UUID;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pedrodlf.iobuilders.bank.transaction.Transaction;
import com.pedrodlf.iobuilders.model.TransactionRequest;

@Component
public class TransactionRequestToTransactionConverter implements Converter<TransactionRequest, Transaction>
{
    @Override
    public Transaction convert(TransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setTransactionID(UUID.randomUUID());
        transaction.setBeneficiaryIBAN(request.getBeneficiaryIBAN());
        transaction.setIssuerIBAN(request.getIssuerIBAN());
        transaction.setDate(new Date(System.currentTimeMillis()));
        transaction.setAmount(request.getAmount());
        return transaction;
    }
}
