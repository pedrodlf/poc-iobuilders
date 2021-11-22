package com.pedrodlf.iobuilders.bank.account.adapter.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pedrodlf.iobuilders.bank.account.Account;
import com.pedrodlf.iobuilders.model.AccountResponse;
import com.pedrodlf.iobuilders.model.Accounts;

@Component
public class AccountListToAccountsConverter implements Converter<List<Account>, Accounts>{

	
	private AccountToAccountResponseConverter converter;
	
	public AccountListToAccountsConverter(AccountToAccountResponseConverter converter) {
		super();
		this.converter = converter;
	}

	@Override
	public Accounts convert(List<Account> source) {
		Accounts accounts = new Accounts();
		accounts.setResults(new BigDecimal(source.size()));
		List<AccountResponse> responses = new ArrayList<AccountResponse>();
		source.forEach(i -> responses.add(converter.convert(i)));
		accounts.setAccounts(responses);
		return accounts;
	}

}
