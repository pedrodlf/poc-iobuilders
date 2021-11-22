package com.pedrodlf.iobuilders.bank.account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pedrodlf.iobuilders.bank.account.adapter.rest.AccountListToAccountsConverter;
import com.pedrodlf.iobuilders.bank.account.adapter.rest.AccountRequestToAccountConverter;
import com.pedrodlf.iobuilders.bank.account.adapter.rest.AccountToAccountResponseConverter;
import com.pedrodlf.iobuilders.bank.exceptions.AccountNotFoundException;
import com.pedrodlf.iobuilders.bank.exceptions.UserNotFoundException;
import com.pedrodlf.iobuilders.bank.user.UserAdapter;
import com.pedrodlf.iobuilders.model.AccountRequest;
import com.pedrodlf.iobuilders.model.AccountResponse;
import com.pedrodlf.iobuilders.model.Accounts;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {

	private AccountRepository accountRepository;
	private UserAdapter adapter;
	private AccountRequestToAccountConverter accountRequestToAccountConverter;
	private AccountToAccountResponseConverter accountToAccountResponseConverter;
	private AccountListToAccountsConverter accountToAccountsConverter;
	
	

	public AccountService(AccountRepository accountRepository, UserAdapter adapter,
			AccountRequestToAccountConverter accountRequestToAccountConverter,
			AccountToAccountResponseConverter accountToAccountResponseConverter,
			AccountListToAccountsConverter accountToAccountsConverter) {
		super();
		this.accountRepository = accountRepository;
		this.adapter = adapter;
		this.accountRequestToAccountConverter = accountRequestToAccountConverter;
		this.accountToAccountResponseConverter = accountToAccountResponseConverter;
		this.accountToAccountsConverter = accountToAccountsConverter;
	}

	public AccountResponse createAccount(AccountRequest accountRequest) {
		if(adapter.isPresent(accountRequest.getUserID())) {
			Account account = accountRequestToAccountConverter.convert(accountRequest);
			final Account savedUser = accountRepository.save(account);
			return accountToAccountResponseConverter.convert(savedUser);
		}else throw new UserNotFoundException("No existe el Usuario "+accountRequest.getUserID()+" , la cuenta no puede ser generada");
	}
	
	public AccountResponse addAmount(UUID accountID, BigDecimal amount){
		Account account = accountRepository.findById(accountID).orElseThrow(AccountNotFoundException::new);
		account.setAmount(account.getAmount().add(amount));
		return accountToAccountResponseConverter.convert(accountRepository.saveAndFlush(account));
	}

	public AccountResponse subtractAmount(UUID accountID, BigDecimal amount){
		Account account = accountRepository.findById(accountID).orElseThrow(AccountNotFoundException::new);
		account.setAmount(account.getAmount().subtract(amount));
		return accountToAccountResponseConverter.convert(accountRepository.saveAndFlush(account));
	}
	
	public Accounts getAccountsByUser(UUID userID) {
		List<Account> accounts = accountRepository.findByUserID(userID);
		return accountToAccountsConverter.convert(accounts);
	}
	
	public AccountResponse getAccount(UUID accountID) {
		Optional<Account> account = accountRepository.findById(accountID);
		if(account.isPresent()) return accountToAccountResponseConverter.convert(account.get());
		else return null;
	}

	public Optional<Account> getOptionalAccountByIBAN(String iban) {
		return accountRepository.findByIban(iban);
	}
	
	
	
}
