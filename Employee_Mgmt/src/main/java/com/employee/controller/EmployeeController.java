package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.employee.entity.Account;
import com.employee.entity.Attendance;
import com.employee.entity.Employee;
import com.employee.repo.EmployeeRepo;
import com.employee.service.EmployeeService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	private RestTemplate restTemplate;



//	@GetMapping("/{id}")
//	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
//		Employee employee = employeeService.getEmployeeById(id);
//		return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
		Optional<Employee> employee = Optional.of(employeeService.getEmployeeById(id));
		return employee.map(emp -> ResponseEntity.ok(emp))
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}


//	@GetMapping
//	public List<Employee> getAllEmployees() {
//		return employeeService.getAllEmployees();
//	}

	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@GetMapping
	public ResponseEntity<List<Employee>> fetchAllEmployee() {
		List<Employee> employeeList = employeeService.fetchAllEmployee();
		return ResponseEntity.ok(employeeList);
	}

	@GetMapping("/{id}/attendance")
	public ResponseEntity<List<Attendance>> getEmployeeAttendance(@PathVariable Long id) {
		ResponseEntity<Attendance[]> response = restTemplate
				.getForEntity("http://attendance-service/attendance/employee/" + id, Attendance[].class);
		Attendance[] attendances = response.getBody();
		return ResponseEntity.ok(Arrays.asList(attendances));
	}

	 @GetMapping("/total-hours")
	    public ResponseEntity<Map<String, Long>> getTotalHoursByEmployee() {
	        Map<String, Long> totalHoursByEmployee = employeeService.fetchTotalHoursByEmployee();
	        return ResponseEntity.ok(totalHoursByEmployee);
	    }

	@GetMapping("/{id}/accounts")
	public ResponseEntity<List<Account>> getEmployeeAccounts(@PathVariable Long id) {
		ResponseEntity<Account[]> response = restTemplate.getForEntity("http://account-service/account/employee/" + id,
				Account[].class);
		Account[] accounts = response.getBody();
		return ResponseEntity.ok(Arrays.asList(accounts));
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateEmployeeById(@PathVariable Long id, @RequestBody Employee employee) {
		String message = employeeService.updateEmployeeById(id, employee);
		if (message.equals("Record not found") || message.equals("Error updating record")) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
		return ResponseEntity.ok(message);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
		String message = employeeService.deleteEmployeeById(id);
		if (message.equals("Record not found") || message.equals("Error deleting record")) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
		return ResponseEntity.ok(message);
	}
}
