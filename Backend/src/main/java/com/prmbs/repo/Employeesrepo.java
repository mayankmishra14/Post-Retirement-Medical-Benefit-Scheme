package com.prmbs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prmbs.dao.Employees;

public interface Employeesrepo extends JpaRepository<Employees,Integer> {

	boolean existsByemployeeId(String employeeid);
}
