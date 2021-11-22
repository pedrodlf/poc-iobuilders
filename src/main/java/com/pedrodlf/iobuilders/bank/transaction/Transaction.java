package com.pedrodlf.iobuilders.bank.transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Transaction {

    @Id
    @Column(name = "id")
    private UUID transactionID;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "beneficiaryIBAN")
    private String beneficiaryIBAN;
    @Column(name = "issuerIBAN")
    private String issuerIBAN;
    @Column(name = "date")
    private Date date;

    public UUID getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(UUID transactionID) {
        this.transactionID = transactionID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBeneficiaryIBAN() {
        return beneficiaryIBAN;
    }

    public void setBeneficiaryIBAN(String beneficiaryIBAN) {
        this.beneficiaryIBAN = beneficiaryIBAN;
    }

    public String getIssuerIBAN() {
        return issuerIBAN;
    }

    public void setIssuerIBAN(String issuerIBAN) {
        this.issuerIBAN = issuerIBAN;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionID, that.transactionID) && Objects.equals(amount, that.amount) && Objects.equals(beneficiaryIBAN, that.beneficiaryIBAN) && Objects.equals(issuerIBAN, that.issuerIBAN) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionID, amount, beneficiaryIBAN, issuerIBAN, date);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", amount=" + amount +
                ", beneficiaryIBAN='" + beneficiaryIBAN + '\'' +
                ", issuerIBAN='" + issuerIBAN + '\'' +
                ", date=" + date +
                '}';
    }
}
