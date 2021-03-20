package com.example.atm;

import com.example.atm.entity.BankAccountHolder;
import com.example.atm.entity.User;
import com.example.atm.model.AtmLocator;
import com.example.atm.repository.BankAccountRepository;
import com.example.atm.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication()
@Transactional
public class AtmApplication implements CommandLineRunner {


	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;






	public static void main(String[] args) {
		SpringApplication.run(AtmApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		User user = new User();
		user.setEnabled(true);
		user.setUsername("sava");
		user.setPassword(passwordEncoder.encode("12345"));
		user.setRole("ROLE_ADMIN");
		userRepository.save(user);

	}
}
