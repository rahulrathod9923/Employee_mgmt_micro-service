package com.account.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

//	List<Account> findEmployeeById(Long id);
//	List<Account> findAttendanceId(Long id);
	List<Account> findByEmployeeId(Long employeeId);
}
