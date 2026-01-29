package com.prmbs.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prmbs.dao.Designation;

public interface Designationrepo extends JpaRepository<Designation,Integer> {
	
	@Query("SELECT d.contribution FROM Designation d WHERE d.designation = :designation")
	Optional<String> getContributionByDesignation(@Param("designation") String designation);

	@Query("SELECT d.benifit FROM Designation d WHERE d.designation = :designation")
	Optional<String> getBenifitByDesignation(@Param("designation") String designation);
}
