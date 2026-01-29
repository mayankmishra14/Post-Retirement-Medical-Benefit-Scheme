package com.prmbs.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;



@Service
public class RazorpayService {

    private final RazorpayClient client;

    public RazorpayService(@Value("${razorpay.key_id}") String key,
                           @Value("${razorpay.key_secret}") String secret) throws RazorpayException {
        this.client = new RazorpayClient(key, secret);
    }

    public Order createOrder(int amount, String receiptId) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", amount);
        options.put("currency", "INR");
        options.put("receipt", receiptId);
        return client.orders.create(options);
    }
}
