package com.pedrodlf.iobuilders.bank.account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
	
	List<Account> findByUserID(UUID UserID);
	Optional<Account> findByIban(String iban);
}
