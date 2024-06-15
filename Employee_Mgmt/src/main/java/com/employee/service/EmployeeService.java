package com.employee.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.employee.entity.Attendance;
import com.employee.entity.Employee;
import com.employee.exceptions.ResourceNotFoundException;
import com.employee.external.services.AccountService;
import com.employee.external.services.AttendanceService;
import com.employee.repo.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepository;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Attendance attendance;

//	@Autowired
//	AccountService accountService;
//
//	public List<Employee> getAllEmployees() {
//		return employeeRepository.findAll();
//	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> fetchAllEmployee() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		return optionalEmployee.orElse(null);
	}

//	public Map<String, Long> fetchTotalHoursByEmployee() {
//		String url = "http://attendance-service/attendance/total-hours";
//		ResponseEntity<Map<Employee, Long>> response = restTemplate.exchange(url, HttpMethod.GET, null,
//				new ParameterizedTypeReference<Map<Employee, Long>>() {
//				});
//
//		return response.getBody().entrySet().stream()
//				.collect(Collectors.toMap(e -> e.getKey().getEmpName(), Map.Entry::getValue));
//	}

	  public Map<String, Long> fetchTotalHoursByEmployee() {
	        String url = "http://attendance-service/attendance/total-hours";
	        ResponseEntity<Map<String, Long>> response = restTemplate.exchange(
	                url,
	                HttpMethod.GET,
	                null,
	                new ParameterizedTypeReference<Map<String, Long>>() {}
	        );

	        return response.getBody();
	    }
	public String updateEmployeeById(Long id, Employee updatedEmployee) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isPresent()) {
			Employee existingEmployee = optionalEmployee.get();
			// Update fields only if they are not null in the updatedEmployee
			if (updatedEmployee.getEmpName() != null) {
				existingEmployee.setEmpName(updatedEmployee.getEmpName());
			}
			if (updatedEmployee.getRole() != null) {
				existingEmployee.setRole(updatedEmployee.getRole());
			}
			if (updatedEmployee.getLocal_Address() != null) {
				existingEmployee.setLocal_Address(updatedEmployee.getLocal_Address());
			}
			if (updatedEmployee.getPermanent_Address() != null) {
				existingEmployee.setPermanent_Address(updatedEmployee.getPermanent_Address());
			}
			if (updatedEmployee.getProject_Assigned() != null) {
				existingEmployee.setProject_Assigned(updatedEmployee.getProject_Assigned());
			}
			if (updatedEmployee.getDaily_Progress() != null) {
				existingEmployee.setDaily_Progress(updatedEmployee.getDaily_Progress());
			}
			employeeRepository.save(existingEmployee);
			return "Updated successfully";
		} else {
			return "Record not found";
		}
	}

	public String deleteEmployeeById(Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isPresent()) {
			employeeRepository.deleteById(id);
			return "Deleted successfully";
		} else {
			return "Record not found";
		}
	}

	public Employee getTotalAttendance(Long id) {
		Employee emp = new Employee();
//		Attendance a = restTemplate.getForObject("http://Attendance_Mgmt/attendance/total/" + emp.getId(),
		Attendance a = restTemplate.getForObject("http://localhost:8020/attendance/total/" + emp.getId(),
				Attendance.class);

		return employeeRepository.findById(id).get();

	}

//	public Employee getEmployeeWithAttendance(Long empId) {
//		// Assuming you have a method in AttendanceService to retrieve attendance by
//		// employee ID
//		Attendance attendance = restTemplate.getForObject("http://Attendance_Mgmt/attendance/{id}", Attendance.class,
//				empId);
//
//		// Assuming you have a method in EmployeeRepository to retrieve employee by ID
//		Employee employee = employeeRepository.findById(empId)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + empId));
//
//		// Set the attendance to the employee
//		employee.setAttendance(attendance);
//
//		return employee;
//	}
//	
//	
}