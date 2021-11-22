package com.pedrodlf.iobuilders.bank.account.adapter.rest;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrodlf.iobuilders.api.AccountsApi;
import com.pedrodlf.iobuilders.bank.account.AccountAdapter;
import com.pedrodlf.iobuilders.model.AccountRequest;
import com.pedrodlf.iobuilders.model.AccountResponse;
import com.pedrodlf.iobuilders.model.Accounts;

@RestController
@RequestMapping("/v1")
public class AccountContollerImpl implements AccountsApi{

	private AccountAdapter accountAdapter;

	public AccountContollerImpl(AccountAdapter accountAdapter) {
		this.accountAdapter = accountAdapter;
	}

	
	public ResponseEntity<AccountResponse> accountsAccountIDGet(UUID accountID) {
		return new ResponseEntity<AccountResponse>(accountAdapter.getAccountByID(accountID),HttpStatus.OK);
	}

	
	public ResponseEntity<AccountResponse> accountsAccountIDPatch(UUID accountID, BigDecimal amount) {
		return new ResponseEntity<AccountResponse>(accountAdapter.addAmount(accountID, amount),HttpStatus.OK);
	}

	
	public ResponseEntity<Accounts> accountsGet(UUID userID) {
		return new ResponseEntity<Accounts>(accountAdapter.getAccountsByUser(userID),HttpStatus.OK);
	}

	
	public ResponseEntity<AccountResponse> accountsPost(AccountRequest acountRequest) {
		return new ResponseEntity<AccountResponse>(accountAdapter.createAccount(acountRequest),HttpStatus.CREATED);
	}

	
	
	
	
	
}
