package com.example.atm.service;

import com.example.atm.entity.BankAccountHolder;
import com.example.atm.entity.BankTransaction;
import com.example.atm.exception.DataNotSaved;
import com.example.atm.model.AtmLocator;
import com.example.atm.model.BankTransactionFullModel;
import com.example.atm.model.BankTransactionModel;
import com.example.atm.model.Type;
import com.example.atm.repository.BankAccountRepository;
import com.example.atm.repository.BankTransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dozermapper.core.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AtmServiceImpl implements AtmService {


    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final BankAccountRepository bankAccountRepository;
    private final BankTransactionRepository bankTransactionRepository;
    private final Mapper mapper;

    public AtmServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper,
                          BankAccountRepository bankAccountRepository, BankTransactionRepository bankTransactionRepository,
                          Mapper mapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.bankAccountRepository = bankAccountRepository;
        this.bankTransactionRepository = bankTransactionRepository;
        this.mapper = mapper;
    }

    @Override
    public List<AtmLocator> findAll() throws JsonProcessingException {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://www.ing.nl/api/locator/atms/", String.class);
        String body = forEntity.getBody();
		List<String> collect = body.lines().collect(Collectors.toList());

        List<AtmLocator> atmLocators = objectMapper.readValue(
                collect.get(1), new TypeReference<List<AtmLocator>>() { });


        return atmLocators.stream().filter(x -> x.getType() == Type.ING).collect(Collectors.toList());

    }

    @Override
    public BankTransactionFullModel save(BankTransactionModel bankTransactionModel) throws DataNotSaved {

        BankAccountHolder byAccountNumber = bankAccountRepository.findByAccountNumber(bankTransactionModel.getBankAccountHolder().getAccountNumber());

        if (byAccountNumber != null) {
            BankTransaction bankTransaction = mapper.map(bankTransactionModel, BankTransaction.class);
            bankTransaction.setBankAccountHolder(byAccountNumber);
            bankTransaction.setCreated_at(new Date());
            byAccountNumber.setAmount((byAccountNumber.getAmount() - bankTransaction.getAmount()));
            this.bankAccountRepository.save(byAccountNumber);
            BankTransaction save = this.bankTransactionRepository.save(bankTransaction);
            return mapper.map(save, BankTransactionFullModel.class);
        }

        throw new DataNotSaved();
    }

    @Override
    public BankTransactionFullModel findTransaction(Long transactionId) {

        Optional<BankTransaction> byId = this.bankTransactionRepository.findById(transactionId);

        BankTransactionFullModel bankTransactionFullModel = new BankTransactionFullModel();

        if (byId.isPresent()) {
            mapper.map(byId.get(), bankTransactionFullModel);
        }
        return bankTransactionFullModel;

    }

    @Override
    public BankTransactionFullModel update(BankTransactionFullModel bankTransactionModel) throws DataNotSaved {
        Optional<BankTransaction> byId = this.bankTransactionRepository.findById(bankTransactionModel.getId());

        if (byId.isPresent()) {
            BankTransaction bankTransaction = byId.get();
            this.mapper.map(bankTransactionModel, bankTransaction);
            BankTransaction save = this.bankTransactionRepository.save(bankTransaction);
            return this.mapper.map(save, BankTransactionFullModel.class);
        }
        throw new DataNotSaved();
    }

    @Override
    public void delete(Long transactionId) {
        Optional<BankTransaction> byId = this.bankTransactionRepository.findById(transactionId);
        if (byId.isPresent()) {
            this.bankTransactionRepository.delete(byId.get());
        }
        //throw exception
    }
}
