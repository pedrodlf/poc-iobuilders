package com.pedrodlf.iobuilders.bank.account.adapter.rest;

import com.pedrodlf.iobuilders.bank.account.Account;
import com.pedrodlf.iobuilders.model.AccountRequest;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class AccountRequestToAccountConverter implements Converter<AccountRequest, Account> {

	@Override
	public Account convert(AccountRequest source) {
		Account account = new Account();
		account.setId(UUID.randomUUID());
		account.setUserId(source.getUserID());
		account.setIban(Iban.random(CountryCode.ES).toString());
		account.setAmount(BigDecimal.ZERO);
		return account;
	}
}