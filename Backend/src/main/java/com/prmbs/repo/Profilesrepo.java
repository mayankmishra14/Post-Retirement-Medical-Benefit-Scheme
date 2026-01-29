package com.prmbs.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prmbs.dao.Profiles;

public interface Profilesrepo extends JpaRepository<Profiles,Integer> {

	boolean existsByphonenumber(String phonenumber);
	Optional<Profiles> findByphonenumber(String phonenumber);
	Profiles findByemployeeId(String employeeId);
}
