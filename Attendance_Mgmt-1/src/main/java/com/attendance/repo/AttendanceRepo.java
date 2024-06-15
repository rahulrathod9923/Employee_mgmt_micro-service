package com.attendance.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.Attendance;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long>{

	int countByTodaysDateBetween(LocalDate startDate, LocalDate endDate);
	List<Attendance> findByEmployeeId(Long employeeId);
}
