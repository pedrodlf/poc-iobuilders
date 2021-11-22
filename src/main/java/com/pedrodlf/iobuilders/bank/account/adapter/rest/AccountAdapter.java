package com.pedrodlf.iobuilders.bank.account.adapter.rest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.pedrodlf.iobuilders.bank.account.Account;
import com.pedrodlf.iobuilders.bank.account.AccountService;
import com.pedrodlf.iobuilders.model.AccountRequest;
import com.pedrodlf.iobuilders.model.AccountResponse;
import com.pedrodlf.iobuilders.model.Accounts;

@Component
public class AccountAdapter {
	
	private AccountService accountService;

	public AccountAdapter(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	public AccountResponse getAccountByID(UUID accountID) {
		return accountService.getAccount(accountID);
	}
	
	public AccountResponse createAccount(AccountRequest acountRequest) {
		return accountService.createAccount(acountRequest);
	}
	
	public Accounts getAccountsByUser(UUID userID) {
	  return accountService.getAccountsByUser(userID);
	}
	
	public AccountResponse addAmount(UUID accountID, BigDecimal amount) {
		return accountService.addAmount(accountID, amount);
	}
	
	public AccountResponse subtractAmount(UUID accountID, BigDecimal amount) {
		return accountService.addAmount(accountID, amount);
	}
	
	public Optional<Account> getOptionalAccountByIBAN(String iban) {
		return accountService.getOptionalAccountByIBAN(iban);
	}

}
