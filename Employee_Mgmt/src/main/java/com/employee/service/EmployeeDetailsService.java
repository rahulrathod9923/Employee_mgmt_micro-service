//package com.employee.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.employee.config.AccountClient;
//import com.employee.config.AttendanceClient;
//import com.employee.entity.Account;
//import com.employee.entity.Attendance;
//
//import java.util.List;
//
//@Service
//public class EmployeeDetailsService {
//    @Autowired
//    private AttendanceClient attendanceClient;
//
//    @Autowired
//    private AccountClient accountClient;
//
//    public List<Attendance> getAllAttendances() {
//        return attendanceClient.getAllAttendances();
//    }
//
//    public List<Account> getAllAccounts() {
//        return accountClient.getAllAccounts();
//    }
//}
//
