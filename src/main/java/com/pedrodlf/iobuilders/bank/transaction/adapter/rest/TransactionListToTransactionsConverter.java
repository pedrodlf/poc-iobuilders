package com.pedrodlf.iobuilders.bank.transaction.adapter.rest;

import com.pedrodlf.iobuilders.bank.transaction.Transaction;
import com.pedrodlf.iobuilders.model.TransactionResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pedrodlf.iobuilders.model.Transactions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionListToTransactionsConverter implements Converter<List<Transaction>, Transactions> {

	private TransactionToTransactionResponseConverter converter;

	public TransactionListToTransactionsConverter(TransactionToTransactionResponseConverter converter) {
		super();
		this.converter = converter;
	}

	@Override
	public Transactions convert(List<Transaction> source) {
		Transactions transactions = new Transactions();
		transactions.setResults(new BigDecimal(source.size()));
		List<TransactionResponse> responses = new ArrayList<TransactionResponse>();
		source.forEach(i -> responses.add(converter.convert(i)));
		transactions.setTransactions(responses);
		return transactions;
	}

}