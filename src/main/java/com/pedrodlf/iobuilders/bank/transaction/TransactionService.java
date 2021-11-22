package com.pedrodlf.iobuilders.bank.transaction;

import com.pedrodlf.iobuilders.bank.account.Account;
import com.pedrodlf.iobuilders.bank.account.AccountAdapter;
import com.pedrodlf.iobuilders.bank.account.adapter.rest.AccountExcepetionHandler;
import com.pedrodlf.iobuilders.bank.account.exception.AccountNotFoundException;
import com.pedrodlf.iobuilders.bank.account.exception.BeneficiaryAccountNotFoundException;
import com.pedrodlf.iobuilders.bank.transaction.adapter.rest.TransactionListToTransactionsConverter;
import com.pedrodlf.iobuilders.bank.transaction.adapter.rest.TransactionRequestToTransactionConverter;
import com.pedrodlf.iobuilders.bank.transaction.adapter.rest.TransactionToTransactionResponseConverter;
import com.pedrodlf.iobuilders.bank.transaction.exception.NoEnoughFundsException;
import com.pedrodlf.iobuilders.model.AccountResponse;
import com.pedrodlf.iobuilders.model.TransactionRequest;
import com.pedrodlf.iobuilders.model.TransactionResponse;
import com.pedrodlf.iobuilders.model.Transactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

	Logger log = LoggerFactory.getLogger(TransactionService.class);
	
	private TransactionRepository transactionRepository;
	private AccountAdapter accountAdapter;
	private TransactionRequestToTransactionConverter transactionRequestToTransactionConverter;
	private TransactionToTransactionResponseConverter transactionToTransactionResponseConverter;
	private TransactionListToTransactionsConverter transactionListToTransactionsConverter;

	

	public TransactionService(TransactionRepository transactionRepository, AccountAdapter accountAdapter,
			TransactionRequestToTransactionConverter transactionRequestToTransactionConverter,
			TransactionToTransactionResponseConverter transactionToTransactionResponseConverter,
			TransactionListToTransactionsConverter transactionListToTransactionsConverter) {
		super();
		this.transactionRepository = transactionRepository;
		this.accountAdapter = accountAdapter;
		this.transactionRequestToTransactionConverter = transactionRequestToTransactionConverter;
		this.transactionToTransactionResponseConverter = transactionToTransactionResponseConverter;
		this.transactionListToTransactionsConverter = transactionListToTransactionsConverter;
	}

	public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
		// Vadidacion de Cuentas
		Account issuer = accountAdapter.getOptionalAccountByIBAN(transactionRequest.getIssuerIBAN()).orElseThrow(AccountNotFoundException::new);
		Account beneficiary = accountAdapter.getOptionalAccountByIBAN(transactionRequest.getBeneficiaryIBAN()).orElseThrow(BeneficiaryAccountNotFoundException::new);
		
		Transaction transaction = transactionRequestToTransactionConverter.convert(transactionRequest);
		//Validación de disposición de efectivo
		if(issuer.getAmount().compareTo(transactionRequest.getAmount())<0) throw new NoEnoughFundsException("La cuenta: "+ issuer.toString() +", no dispone de Fondos Suficientes");
		//Modificación de Saldos
		accountAdapter.subtractAmount(issuer.getId(), transactionRequest.getAmount());
		accountAdapter.addAmount(beneficiary.getId(), transactionRequest.getAmount());
		//Persistir transacción
		final Transaction savedTransaction = transactionRepository.save(transaction);
		return transactionToTransactionResponseConverter.convert(savedTransaction);
	}

	public TransactionResponse getTransactionById(UUID transactionId) {
		// TODO: No es la excepción correcta, hay que manejarlas con el exception
		// handler y devolver un NotFoundException personalizado
		Transaction transaction = transactionRepository.findById(transactionId)
				.orElseThrow(IllegalArgumentException::new);
		return transactionToTransactionResponseConverter.convert(transaction);
	}

	public Transactions getTransactionsByAccount(String iban) {
		List<Transaction> list = transactionListParser(
				transactionRepository.findByBeneficiaryIBANOrIssuerIBAN(iban, iban), iban);
		return transactionListToTransactionsConverter.convert(list);
	}

	private List<Transaction> transactionListParser(List<Transaction> list, String Iban) {
		list.forEach(i -> {
			if (i.getIssuerIBAN().equalsIgnoreCase(Iban))
				i.setAmount(i.getAmount().negate());
		});
		return list;
	}
	
	
}
