package com.prmbs.service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class OtpService {

	private final Map<String, Integer> otpMap = new ConcurrentHashMap<>();

    public int generateOtp(String phone) {
        int otp = new Random().nextInt(900000) + 100000;
        otpMap.put(phone, otp);
        return otp;
    }

    public boolean verifyOtp(String phone, int otp) {
        return otpMap.containsKey(phone) && otpMap.get(phone) == otp;
    }
}