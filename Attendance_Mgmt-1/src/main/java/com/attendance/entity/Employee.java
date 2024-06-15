package com.attendance.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("empName")
	private String empName;
	
	@JsonProperty("role")
	private String role;
	
	@JsonProperty("localAddress")
	private String localAddress;
	
	@JsonProperty("permanentAddress")
	private String permanentAddress;
	
	@JsonProperty("projectAssigned")
	private String projectAssigned;
	
	@JsonProperty("dailyProgress")
	private String dailyProgress;
}

