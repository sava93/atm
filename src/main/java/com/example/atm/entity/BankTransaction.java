package com.example.atm.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bank_transaction")
public class BankTransaction {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    private double amount;

    private String device_information;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "bank_holder_id")
    private BankAccountHolder bankAccountHolder;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDevice_information() {
        return device_information;
    }

    public void setDevice_information(String device_information) {
        this.device_information = device_information;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public BankAccountHolder getBankAccountHolder() {
        return bankAccountHolder;
    }

    public void setBankAccountHolder(BankAccountHolder bankAccountHolder) {
        this.bankAccountHolder = bankAccountHolder;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof BankTransaction) {
            BankTransaction transaction = (BankTransaction) o;
            return this.getId().equals(transaction.getId());
        }
        return false;

    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", device_information='" + device_information + '\'' +
                ", created_at=" + created_at +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
