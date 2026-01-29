package com.prmbs.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Designation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String designation;
	
	private String contribution;
	
	private String benifit;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public String getContribution() {
		return contribution;
	}

	public void setContribution(String contribution) {
		this.contribution = contribution;
	}

	public String getBenifit() {
		return benifit;
	}

	public void setBenifit(String benifit) {
		this.benifit = benifit;
	}
	
	
}
