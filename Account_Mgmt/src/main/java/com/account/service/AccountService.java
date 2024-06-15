package com.account.service;

import com.account.entity.Account;
import com.account.entity.Employee;
import com.account.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepository;
    
    @Autowired
    private RestTemplate restTemplate;

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> getAccountsByEmployeeId(Long employeeId) {
        return accountRepository.findByEmployeeId(employeeId);
    }

    public Employee getEmployeeById(Long employeeId) {
        return restTemplate.getForObject("http://employee-service/employee/" + employeeId, Employee.class);
    }

    public Account saveAccountDetails(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> fetchAllAccountDetails() {
        return accountRepository.findAll();
    }

    public Account fetchAccountDetailsById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return optionalAccount.orElse(null);
    }

//    public List<Account> getAccountByEmpId(Long id) {
//		return accountRepository.findEmployeeById(id);
//	}
//    
//    public List<Account> getAccountByAttendancelId(Long id) {
//		return accountRepository.findAttendanceId(id);
//	}

    
    public String updateAccountDetailsById(Long id, Account updatedAccount) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account existingAccount = optionalAccount.get();
            // Update fields only if they are not null in the updatedAccount
      
            if (updatedAccount.getSalary() != null) {
                existingAccount.setSalary(updatedAccount.getSalary());
            }
            if (updatedAccount.getSalaryByAttendance() != null) {
                existingAccount.setSalaryByAttendance(updatedAccount.getSalaryByAttendance());
            }
            if (updatedAccount.getSalaryPaid() != null) {
                existingAccount.setSalaryPaid(updatedAccount.getSalaryPaid());
            }
            if (updatedAccount.getSalaryRemain() != null) {
                existingAccount.setSalaryRemain(updatedAccount.getSalaryRemain());
            }
            accountRepository.save(existingAccount);
            return "Updated successfully";
        } else {
            return "Record not found";
        }
    }

    public String deleteAccountById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            accountRepository.deleteById(id);
            return "Deleted successfully";
        } else {
            return "Record not found";
        }
    }
}
