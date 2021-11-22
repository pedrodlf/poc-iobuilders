package com.pedrodlf.iobuilders.bank.transaction.adapter.rest;

import com.pedrodlf.iobuilders.api.TransactionsApi;
import com.pedrodlf.iobuilders.bank.transaction.TransactionAdapter;
import com.pedrodlf.iobuilders.model.TransactionRequest;
import com.pedrodlf.iobuilders.model.TransactionResponse;
import com.pedrodlf.iobuilders.model.Transactions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class TransactionControllerImpl implements TransactionsApi {

    private TransactionAdapter transactionAdapter;

    public TransactionControllerImpl(TransactionAdapter transactionAdapter){
        this.transactionAdapter = transactionAdapter;
    }

    @Override
    public ResponseEntity<TransactionResponse> transactionsPost(TransactionRequest transactionRequest) {
        return new ResponseEntity<TransactionResponse>(transactionAdapter.createTransaction(transactionRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TransactionResponse> transactionsTransactionIDGet(UUID transactionID) {
        return new ResponseEntity<TransactionResponse>(transactionAdapter.getTransactionById(transactionID), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Transactions> transactionsGet(String iban) {
        return new ResponseEntity<Transactions>(transactionAdapter.getTransactionsByAccount(iban), HttpStatus.OK);

    }
}
