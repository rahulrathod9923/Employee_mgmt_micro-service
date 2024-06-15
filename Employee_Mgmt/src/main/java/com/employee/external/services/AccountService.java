package com.employee.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.entity.Account;

@FeignClient(name="Account_Mgmt")
public interface AccountService {


	//get
	@GetMapping("/account/fetch")
	public ResponseEntity<Account> fetchAccountDetails(@RequestParam Long id);
	
	//post
	@PostMapping("/account/save")
	public ResponseEntity<Account> saveAccountDetails(Account values);
	
	//put
	@PutMapping("/account/update/{id}")
	public ResponseEntity<Account> updateAccountDetailsById(@PathVariable("id") Long Id,Account values);
	
	//delete
	@DeleteMapping("/account/delete/{id}")
	public void deleteAccount(@PathVariable Long id);
}
