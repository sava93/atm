package com.example.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTransactionRepository extends JpaRepository<com.example.atm.entity.BankTransaction, Long> {
}
