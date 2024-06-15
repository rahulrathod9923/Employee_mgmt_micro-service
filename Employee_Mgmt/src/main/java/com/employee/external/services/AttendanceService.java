package com.employee.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.employee.entity.Attendance;

@FeignClient(name="Attendance_Mgmt")
public interface AttendanceService {


	//get
	
	
	//post
	@PostMapping("/attendance/create")
	public ResponseEntity<Attendance> createAttendance(Attendance values);
	
	//put
	@PutMapping("/attendance/update/{id}")
	public ResponseEntity<Attendance> updateAttendance(@PathVariable("id") Long Id,Attendance values);
	
	//delete
	@DeleteMapping("/attendance/delete/{id}")
	public void deleteAttendance(@PathVariable Long id);
}
