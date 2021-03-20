package com.example.atm.repository;

import com.example.atm.entity.BankAccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface BankAccountRepository extends JpaRepository<BankAccountHolder, Long> {

    public BankAccountHolder findByAccountNumber(@Param("accountNumber") String accountNumber);
}
