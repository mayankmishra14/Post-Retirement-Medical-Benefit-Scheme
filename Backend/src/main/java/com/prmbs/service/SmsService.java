package com.prmbs.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


import jakarta.annotation.PostConstruct;

@Service
public class SmsService {

    @Value("${twilio.account.sid}")
    private String sid;

    @Value("${twilio.auth.token}")
    private String token;

    @Value("${twilio.phone.number}")
    private String fromNumber;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(sid, token);
    }

    public void sendSms(String to, String body) {
        Message.creator(new PhoneNumber(to), new PhoneNumber(fromNumber), body).create();
    }
}