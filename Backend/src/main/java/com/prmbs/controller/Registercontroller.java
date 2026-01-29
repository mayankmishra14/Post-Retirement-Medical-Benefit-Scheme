package com.prmbs.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prmbs.dao.Employees;
import com.prmbs.dao.Logincredentials;
import com.prmbs.dao.Profiles;
import com.prmbs.repo.Employeesrepo;
import com.prmbs.repo.Logincredentialsrepo;
import com.prmbs.repo.Profilesrepo;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins={"http://localhost:3000", "http://192.168.4.90:3000"})
public class Registercontroller {

	@Autowired
	private Employeesrepo emprepo;
	
	@Autowired
	private Profilesrepo profilerepo;
	
	
	@Autowired
	private Logincredentialsrepo credentialrepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping("/verify-emp")
	public ResponseEntity<Map<String,String>> verifyemp(@RequestBody Employees emp)
	{
		System.out.println(emp.getEmployeeId());
		if(emprepo.existsByemployeeId(emp.getEmployeeId()))
			return ResponseEntity.ok(Map.of("valid","valid"));
		else
			return ResponseEntity.ok(Map.of("valid","invalid"));
	}
	
	@PostMapping("/store-emp")
	public String addemp(@RequestBody Profiles profile, @RequestBody MultipartFile empimg, @RequestBody MultipartFile spouseimg) throws IOException
	{
		profile.setEmpimg(empimg.getBytes());
		profile.setSpouseimg(spouseimg.getBytes());
		profilerepo.save(profile);
		return "Registered Successfully";
	}
	
	@PostMapping("/register-emp")
	 public ResponseEntity<String> register(@RequestBody Logincredentials user) {
        user.setPassword(encoder.encode(user.getPassword()));
        credentialrepo.save(user);
        return ResponseEntity.ok("Registered Successfully");
    }

	
}
