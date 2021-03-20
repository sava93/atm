package com.example.atm;

import com.example.atm.entity.BankAccountHolder;
import com.example.atm.entity.BankTransaction;
import com.example.atm.repository.BankAccountRepository;
import com.example.atm.repository.BankTransactionRepository;
import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class AtmApplicationTests {


	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Autowired
	private BankTransactionRepository bankTransactionRepository;


	@BeforeEach
	public void createData() {
		this.bankTransactionRepository.deleteAll();
		this.bankAccountRepository.deleteAll();
		BankAccountHolder bankAccountHolder = new BankAccountHolder();
		bankAccountHolder.setAmount(2000000.0);
		bankAccountHolder.setAccountNumber("934850439-43543543");
		bankAccountHolder.setFirstName("sava");
		bankAccountHolder.setLastName("stojkovic");
		this.bankAccountRepository.save(bankAccountHolder);


	}


	@Test
	void testData() {

		BankAccountHolder byAccountNumber = this.bankAccountRepository.findByAccountNumber("934850439-43543543");
		BankTransaction bankTransaction = new BankTransaction();
		bankTransaction.setCreated_at(new Date());
		bankTransaction.setAmount(100.0);
		bankTransaction.setDevice_information("some device");
		bankTransaction.setBankAccountHolder(byAccountNumber);

		this.bankTransactionRepository.save(bankTransaction);

		BankTransaction bankTransaction1 = new BankTransaction();
		bankTransaction1.setCreated_at(new Date());
		bankTransaction1.setAmount(300.0);
		bankTransaction1.setDevice_information("some device");
		bankTransaction1.setBankAccountHolder(byAccountNumber);

		this.bankTransactionRepository.save(bankTransaction1);

		List<BankTransaction> all = this.bankTransactionRepository.findAll();

		Assertions.assertEquals(CollectionUtils.isNotEmpty(all), true);
		Assertions.assertEquals(all.size(), 2);

	}

	@Test
	void testDelete() {

		BankAccountHolder byAccountNumber = this.bankAccountRepository.findByAccountNumber("934850439-43543543");
		BankTransaction bankTransaction = new BankTransaction();
		bankTransaction.setCreated_at(new Date());
		bankTransaction.setAmount(100.0);
		bankTransaction.setDevice_information("some device");
		bankTransaction.setBankAccountHolder(byAccountNumber);

		BankTransaction save = this.bankTransactionRepository.save(bankTransaction);

		Long id = save.getId();

		BankTransaction bankTransaction1 = new BankTransaction();
		bankTransaction1.setCreated_at(new Date());
		bankTransaction1.setAmount(300.0);
		bankTransaction1.setDevice_information("some device");
		bankTransaction1.setBankAccountHolder(byAccountNumber);

		this.bankTransactionRepository.save(bankTransaction1);

		this.bankTransactionRepository.deleteById(id);

		List<BankTransaction> all = this.bankTransactionRepository.findAll();

		Assertions.assertEquals(CollectionUtils.isNotEmpty(all), true);
		Assertions.assertEquals(all.size(), 1);

		for (BankTransaction transaction : all) {
			Assertions.assertNotEquals(transaction.getId(), id);
		}

	}

}
