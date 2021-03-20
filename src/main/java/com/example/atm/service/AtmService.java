package com.example.atm.service;

import com.example.atm.exception.DataNotSaved;
import com.example.atm.model.AtmLocator;
import com.example.atm.model.BankTransactionFullModel;
import com.example.atm.model.BankTransactionModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AtmService {


    List<AtmLocator> findAll() throws JsonProcessingException;

    BankTransactionFullModel save(BankTransactionModel bankTransactionModel) throws DataNotSaved;

    BankTransactionFullModel findTransaction(Long transactionId);

    BankTransactionFullModel update(BankTransactionFullModel bankTransactionModel) throws DataNotSaved;

    void delete(Long transactionId);


}
