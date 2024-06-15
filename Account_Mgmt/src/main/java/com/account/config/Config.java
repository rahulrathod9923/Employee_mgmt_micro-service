package com.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.account.entity.Attendance;
import com.account.entity.Employee;


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
	Attendance account() {
		return new Attendance();
	}
	
}
