package com.pedrodlf.iobuilders.bank.account.adapter.rest;

import java.math.BigDecimal;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pedrodlf.iobuilders.bank.account.Account;
import com.pedrodlf.iobuilders.model.AccountResponse;

@Component
public class AccountToAccountResponseConverter implements Converter<Account, AccountResponse> {

	public static final String URI_USER = "/users/";
	public static final String URI = "/accounts/";
	
	@Override
	public AccountResponse convert(Account source) {
		AccountResponse accountResponse = new AccountResponse();
		accountResponse.setAccountID(source.getId());
		accountResponse.setAmount(source.getAmount());
		accountResponse.setOwner(URI_USER+source.getUserId());
		accountResponse.setUri(URI+source.getId());
		accountResponse.setIban(source.getIban());
		return accountResponse;
	}

	
}
