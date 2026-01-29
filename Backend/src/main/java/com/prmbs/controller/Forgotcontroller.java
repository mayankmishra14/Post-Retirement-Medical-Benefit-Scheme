package com.prmbs.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prmbs.dao.Logincredentials;
import com.prmbs.dao.Profiles;
import com.prmbs.repo.Logincredentialsrepo;
import com.prmbs.repo.Profilesrepo;
import com.prmbs.service.OtpService;
import com.prmbs.service.SmsService;

@RestController
@RequestMapping("/forgot")
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.4.90:3000"})
public class Forgotcontroller {

    @Autowired
    private OtpService otpService;

    @Autowired
    private SmsService smsService;
    
    @Autowired
    private Profilesrepo profilerepo;
    
    @Autowired
    private Logincredentialsrepo credentialrepo;
    
    @Autowired
	private PasswordEncoder encoder;

    @PostMapping("/verify-num")
    public ResponseEntity<Map<String,String>> verifynum(@RequestBody Map<String,String> req)
    {
    	String x = req.get("phoneNumber");
    	
    	if(profilerepo.existsByphonenumber(x))
    	{
    		 x="+91"+x;
    		 int otp = otpService.generateOtp(x);
    	        smsService.sendSms(x, "Your OTP is: " + otp);
    	        return ResponseEntity.ok(Map.of("valid","sent"));
    	}
    	else
    		return ResponseEntity.ok(Map.of("valid","error"));
    		
    	
    }
    @PostMapping("/resend-otp")
    public ResponseEntity<Map<String,String>> sendOtp(@RequestBody Map<String,String> req) {
        String x = req.get("phoneNumber");
        x="+91"+x;
        int otp = otpService.generateOtp(x);
        smsService.sendSms(x, "Your OTP is: " + otp);
        return ResponseEntity.ok(Map.of("valid","sent"));
    }

    @PostMapping("/verify-otp")
    public  ResponseEntity<String> verifyOtp(@RequestBody Map<String,String> req) {
    	String x = req.get("phoneNumber");
    	String employeeId = profilerepo.findByphonenumber(x).map(Profiles::getEmployeeId).orElse(null); 
    	x="+91"+x;
    	int otp = Integer.parseInt(req.get("code"));
    	boolean isValid = otpService.verifyOtp(x,otp);
    	System.out.println(isValid);
        return isValid ? ResponseEntity.ok(employeeId) 
                       : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP.");
    }
    
    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody Logincredentials user) {
        Optional<Logincredentials> existingUser = credentialrepo.findByusername(user.getUsername());

        if (existingUser.isPresent()) {
            Logincredentials updatedUser = existingUser.get();
            updatedUser.setPassword(encoder.encode(user.getPassword()));
            credentialrepo.save(updatedUser);
            return ResponseEntity.ok("Password reset successful");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }


}
