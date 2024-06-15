package com.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.attendance.entity.Attendance;
import com.attendance.entity.Employee;
import com.attendance.service.AttendanceService;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    
//    @Autowired
//    private EmployeeClient employeeClient;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @PostMapping
    public Attendance saveAttendance(@RequestBody Attendance attendance) {
        Employee employee = attendanceService.getEmployeeById(attendance.getEmployeeId());
        if (employee != null) {
            attendance.setEmpName(employee.getEmpName());
        }
        return attendanceService.saveAttendance(attendance);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Attendance> getAttendanceByEmployeeId(@PathVariable Long employeeId) {
        return attendanceService.getAttendanceByEmployeeId(employeeId);
    }
    
    @GetMapping("/all")
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

//    public Employee getEmployeeById(Long id) {
//        String employeeServiceUrl = "http://Employee-Mgmt/employee/fetch" + id;
//        ResponseEntity<Employee> response = restTemplate.getForEntity(employeeServiceUrl, Employee.class);
//        return response.getBody();
//    }

//    @GetMapping("/{empId}")
//    public ResponseEntity<Map<String, String>> getAttendance(@PathVariable Long empId) {
//        Employee employee = employeeClient.getEmployee(empId);
//        Map<String, String> response = new HashMap<>();
//        response.put("empId", empId.toString());
//        response.put("empName", employee.getEmpName());
//        response.put("attendance", "Present");
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/save")
    public Attendance postMethodName(@RequestBody Attendance attendance) { 
        return attendanceService.save(attendance);
    }
    

    @GetMapping("/total")
    public int getTotalAttendanceForMonth(@RequestParam int year, @RequestParam int month) {
        return attendanceService.getTotalAttendanceForMonth(year, month);
    }
    
    @GetMapping("/total-hours")
    public ResponseEntity<Map<String, Long>> getTotalHoursByEmployee() {
        Map<String, Long> totalHoursByEmployee = attendanceService.getTotalHoursByEmployee();
        return ResponseEntity.ok(totalHoursByEmployee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAttendanceById(@PathVariable Long id, @RequestBody Attendance attendance) {
        String message = attendanceService.updateAttendance(id, attendance);
        if (message.equals("Record not found") || message.equals("Error updating record")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAttendanceById(@PathVariable Long id) {
        String message = attendanceService.deleteAttendance(id);
        if (message.equals("Record not found") || message.equals("Error deleting record")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }
}
