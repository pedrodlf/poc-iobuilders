package com.pedrodlf.iobuilders.bank.transaction.adapter.rest;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrodlf.iobuilders.api.TransactionsApi;
import com.pedrodlf.iobuilders.model.TransactionRequest;
import com.pedrodlf.iobuilders.model.TransactionResponse;
import com.pedrodlf.iobuilders.model.Transactions;

@RestController
@RequestMapping("/v1")
public class TransactionControllerImpl implements TransactionsApi {

	Logger log = LoggerFactory.getLogger(TransactionControllerImpl.class);
	
    private TransactionAdapter transactionAdapter;

    public TransactionControllerImpl(TransactionAdapter transactionAdapter){
        this.transactionAdapter = transactionAdapter;
    }

    @Override
    public ResponseEntity<TransactionResponse> transactionsPost(TransactionRequest transactionRequest) {
    	log.info("transactionsPost {}", transactionRequest.getIssuerIBAN());
        return new ResponseEntity<TransactionResponse>(transactionAdapter.createTransaction(transactionRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TransactionResponse> transactionsTransactionIDGet(UUID transactionID) {
    	log.info("transactionsTransactionIDGet {}", transactionID);
        return new ResponseEntity<TransactionResponse>(transactionAdapter.getTransactionById(transactionID), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Transactions> transactionsGet(String iban) {
    	log.info("transactionsGet {}", iban);
        return new ResponseEntity<Transactions>(transactionAdapter.getTransactionsByAccount(iban), HttpStatus.OK);

    }
}
