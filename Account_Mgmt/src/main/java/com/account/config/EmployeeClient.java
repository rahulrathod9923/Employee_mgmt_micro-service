//package com.account.config;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.account.entity.Employee;
//
//
//@FeignClient(name = "Employee_Mgmt", url = "http://localhost:8010")
//public interface EmployeeClient {
//	
//    @GetMapping("/employee/fetch/{id}")
//    Employee getEmployee(@PathVariable("id") Long id);
//}
//
