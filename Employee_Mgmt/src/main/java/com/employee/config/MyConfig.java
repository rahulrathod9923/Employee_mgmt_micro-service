package com.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.employee.entity.Account;
import com.employee.entity.Attendance;

@Configuration
public class MyConfig {

//	@Bean
//	RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

	@Bean
	Attendance attendance() {
		return new Attendance();
	}

	@Bean
	Account account() {
		return new Account();
	}

}
