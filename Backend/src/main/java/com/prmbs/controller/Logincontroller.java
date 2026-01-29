package com.prmbs.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prmbs.component.JwtUtil;
import com.prmbs.dao.Logincredentials;
import com.prmbs.dao.Profiles;
import com.prmbs.repo.Logincredentialsrepo;
import com.prmbs.repo.Profilesrepo;


@RestController
@RequestMapping("/login")
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.4.90:3000"})
public class Logincontroller {

	@Autowired
	private Logincredentialsrepo credentialrepo;
	
	@Autowired
	private Profilesrepo profilerepo;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder encoder;

	
	@PostMapping("/verify-login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Logincredentials user) 
	{
		System.out.println(user.getPassword());
	    Optional<Logincredentials> existingUser = credentialrepo.findByusername(user.getUsername());

	    if (existingUser.isPresent()) {
	        Logincredentials login = existingUser.get();

	        if (user != null && encoder.matches(user.getPassword(),  login.getPassword())) {
	            String token = jwtUtil.generateToken(user.getUsername());
	            return ResponseEntity.ok(Map.of("token", token));
	        } else {
	            
	            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
	        }
	    } else {
	        
	        return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
	    }
	}

	 
	 @PostMapping("/profile")
	 public Profiles userprofile(@RequestBody String req)
	 {
		 
		 Profiles usr = profilerepo.findByemployeeId(req);
		 return usr;
	 }
}
