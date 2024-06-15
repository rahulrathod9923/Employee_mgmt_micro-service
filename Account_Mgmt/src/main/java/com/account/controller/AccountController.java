package com.account.controller;

import com.account.entity.Account;
import com.account.entity.Employee;
import com.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	RestTemplate restTemplate;

//	 @PostMapping
//	    public Account saveAccount(@RequestBody Account account) {
//	        Employee employee = accountService.getEmployeeById(account.getEmployeeId());
//	        if (employee != null) {
//	            account.setEmpName(employee.getEmpName());
//	        }
//	        return accountService.saveAccount(account);
//	    }

	@GetMapping("/employee/{employeeId}")
	public List<Account> getAccountsByEmployeeId(@PathVariable Long employeeId) {
		return accountService.getAccountsByEmployeeId(employeeId);
	}

//	@GetMapping("/{empId}")
//	public ResponseEntity<Map<String, String>> getAccount(@PathVariable Long empId) {
//		Employee employee = employeeClient.getEmployee(empId);
//		Map<String, String> response = new HashMap<>();
//		response.put("empId", empId.toString());
//		response.put("empName", employee.getEmpName());
//		response.put("accountStatus", "Active");
//		return ResponseEntity.ok(response);
//	}
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping
	public ResponseEntity<Account> saveAccountDetails(@RequestBody Account account) {
		Account savedAccount = accountService.saveAccountDetails(account);
		return ResponseEntity.ok(savedAccount);
	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<Account> fetchAccountDetails(@RequestParam Long id) {
		Account account = accountService.fetchAccountDetailsById(id);
		if (account == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(account);
	}

	@GetMapping("/fetchAll")
	public ResponseEntity<List<Account>> fetchAllAccountDetails() {
		List<Account> accountList = accountService.fetchAllAccountDetails();
		return ResponseEntity.ok(accountList);
	}

	// get all by Employee id
// 	@GetMapping("/epmloyee/{Id}")
// 	public ResponseEntity<List<Account>> getAccountByEmpId(@PathVariable Long id) {
// 		return ResponseEntity.ok(accountService.getAccountByEmpId(id));
// 	}
// 	
// // get all by Attendance id
// 	@GetMapping("/attendance/{Id}")
// 	public ResponseEntity<List<Account>> getAccountByAttendancelId(@PathVariable Long id) {
// 		return ResponseEntity.ok(accountService.getAccountByAttendancelId(id));
// 	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateAccountDetailsById(@PathVariable Long id, @RequestBody Account account) {
		String message = accountService.updateAccountDetailsById(id, account);
		if (message.equals("Record not found") || message.equals("Error updating record")) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
		return ResponseEntity.ok(message);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
		String message = accountService.deleteAccountById(id);
		if (message.equals("Record not found") || message.equals("Error deleting record")) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
		return ResponseEntity.ok(message);
	}
}
