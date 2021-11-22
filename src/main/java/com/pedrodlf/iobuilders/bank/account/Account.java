package com.pedrodlf.iobuilders.bank.account;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	
	@Id
    @Column(name = "id")
    private UUID id;
	@Column(name = "userId")
    private UUID userID;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column (name = "iban",unique=true)
    private String iban;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getUserId() {
		return userID;
	}

	public void setUserId(UUID userId) {
		this.userID = userId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
	
	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userID + ", amount=" + amount + ", iban=" + iban + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, iban, id, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(iban, other.iban) && Objects.equals(id, other.id)
				&& Objects.equals(userID, other.userID);
	}

	
    
    
}
