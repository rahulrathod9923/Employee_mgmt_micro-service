package com.account.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;
	
	String empName;
	String role;
	String local_Address;
	String permanent_Address;
	String project_Assigned;
	String daily_Progress;
}
