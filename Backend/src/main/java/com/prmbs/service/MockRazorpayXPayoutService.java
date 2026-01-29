package com.prmbs.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class MockRazorpayXPayoutService {

    public String createContact(String name, String email, String phone) {
        return "mock_contact_" + UUID.randomUUID().toString();
    }

    public String createFundAccount(String contactId, String accountHolderName, String ifsc, String accountNumber) {
        return "mock_fund_account_" + UUID.randomUUID().toString();
    }

    public String initiatePayout(String fundAccountId, int amount, String currency, String narration) {
        return "mock_payout_" + UUID.randomUUID().toString();
    }
}