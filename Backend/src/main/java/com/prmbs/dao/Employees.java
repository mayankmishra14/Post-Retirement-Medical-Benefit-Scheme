package com.prmbs.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employees {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(unique=true)
	private String employeeId;
	
	@Column(unique=true)
	private String medicalId;
	
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getMedicalId() {
		return medicalId;
	}
	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}
	
}
