package com.attendance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.attendance.entity.Account;
import com.attendance.entity.Employee;


@Configuration
public class Config {

//	@Bean
//	RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

	@Bean
	Employee attendance() {
		return new Employee();
	}

	@Bean
	Account account() {
		return new Account();
	}
	
}
