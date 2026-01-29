package com.prmbs.dao;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Profiles {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	private String Name;
	
	private String employeeId;
	
	private String medicalId;
	
	private LocalDate Appointment;
	
	private LocalDate Retirement;
	
	private String Designation;
	
	private String Lastdrawnpay;
	
	private String ResidentialAddress;
	
	private String Aadharno;
	
	private String Panno;
	
	private String Spousename;
	
	private String Spouseaddr;
	
	private String Spousepay;
	
	private String Spouseaadhar;
	
	private String Spousepan;
	
	@Column(unique=true)
	private String phonenumber;
	
	@Lob
	private byte[] empimg;
	
	@Lob
	private byte[] spouseimg;

	public byte[] getEmpimg() {
		return empimg;
	}

	public void setEmpimg(byte[] empimg) {
		this.empimg = empimg;
	}

	public byte[] getSpouseimg() {
		return spouseimg;
	}

	public void setSpouseimg(byte[] spouseimg) {
		this.spouseimg = spouseimg;
	}

	public String getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getAppointment() {
		return Appointment;
	}

	public void setAppointment(LocalDate appointment) {
		Appointment = appointment;
	}

	public LocalDate getRetirement() {
		return Retirement;
	}

	public void setRetirement(LocalDate retirement) {
		Retirement = retirement;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public String getLastdrawnpay() {
		return Lastdrawnpay;
	}

	public void setLastdrawnpay(String lastdrawnpay) {
		Lastdrawnpay = lastdrawnpay;
	}

	public String getResidentialAddress() {
		return ResidentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		ResidentialAddress = residentialAddress;
	}

	public String getAadharno() {
		return Aadharno;
	}

	public void setAadharno(String aadharno) {
		Aadharno = aadharno;
	}

	public String getPanno() {
		return Panno;
	}

	public void setPanno(String panno) {
		Panno = panno;
	}

	public String getSpousename() {
		return Spousename;
	}

	public void setSpousename(String spousename) {
		Spousename = spousename;
	}

	public String getSpouseaddr() {
		return Spouseaddr;
	}

	public void setSpouseaddr(String spouseaddr) {
		Spouseaddr = spouseaddr;
	}

	public String getSpousepay() {
		return Spousepay;
	}

	public void setSpousepay(String spousepay) {
		Spousepay = spousepay;
	}

	public String getSpouseaadhar() {
		return Spouseaadhar;
	}

	public void setSpouseaadhar(String spouseaadhar) {
		Spouseaadhar = spouseaadhar;
	}

	public String getSpousepan() {
		return Spousepan;
	}

	public void setSpousepan(String spousepan) {
		Spousepan = spousepan;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	
}
