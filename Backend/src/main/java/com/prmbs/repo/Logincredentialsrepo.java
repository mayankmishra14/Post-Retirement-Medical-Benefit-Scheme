package com.prmbs.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prmbs.dao.Logincredentials;

public interface Logincredentialsrepo extends JpaRepository<Logincredentials,Integer> {

	Optional<Logincredentials> findByusername(String username);

}
