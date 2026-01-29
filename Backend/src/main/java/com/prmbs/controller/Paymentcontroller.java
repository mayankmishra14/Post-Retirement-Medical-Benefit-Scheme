package com.prmbs.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prmbs.dao.Designation;
import com.prmbs.dao.PaymentRecord;
import com.prmbs.repo.Designationrepo;
import com.prmbs.repo.Paymentrepo;
import com.prmbs.service.MockRazorpayXPayoutService;
import com.prmbs.service.RazorpayService;
import com.razorpay.Order;


@RestController
@RequestMapping("/payment")
@CrossOrigin(origins={"http://localhost:3000", "http://192.168.4.90:3000"})
public class Paymentcontroller {

	
	 @Autowired
	    private RazorpayService razorpayService;

	    @Autowired
	    private Paymentrepo paymentrepo;
	    
	    @Autowired
	    private MockRazorpayXPayoutService mockPayoutService;
	    
	    @Autowired
	    private Designationrepo desigrepo;
	   
	    
	    @PostMapping("/create")
	    public ResponseEntity<?> createPayment(@RequestBody Map<String, String> request) {
	        String username = request.get("username");
	        String desig = request.get("designation");
	        String contribution = desigrepo
	        	    .getContributionByDesignation(desig)
	        	    .orElse("Not Found");
	        contribution=contribution+"00";

	        try {
	        	String shortReceiptId = "receipt_" + UUID.randomUUID().toString().substring(0, 25);
	            Order order = razorpayService.createOrder(Integer.parseInt(contribution), shortReceiptId);

	            PaymentRecord record = new PaymentRecord();
	            record.setUsername(username);
	            record.setOrderId(order.get("id"));
	            record.setAmount(Long.valueOf(order.get("amount").toString()));
	            record.setStatus("CREATED");
	            record.setCreatedAt(LocalDateTime.now());

	            paymentrepo.save(record);

	            return ResponseEntity.ok(Map.of(
	                "orderId", order.get("id"),
	              // "key", order.get("key"),
	                "amount", Long.valueOf(order.get("amount").toString()),
	                "currency", order.get("currency"),
	                "username", username
	            ));
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	        }
	    }
	    @PostMapping("/confirm")
	    public ResponseEntity<?> confirmPayment(@RequestBody Map<String, String> request) {
	        String orderId = request.get("orderId");
	        String paymentId = request.get("paymentId");
	        String status = request.get("status");
	        String benifit = desigrepo
	        	    .getBenifitByDesignation(request.get("desig"))
	        	    .orElse("Not Found");
	        System.out.println(benifit);
	        return paymentrepo.findByOrderId(orderId)
	            .map(record -> {
	                record.setPaymentId(paymentId);
	                record.setStatus(status);
	                paymentrepo.save(record);
	                return ResponseEntity.ok(benifit);
	            })
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found"));
	    }
	    
	    @PostMapping("/payout")
	    public ResponseEntity<?> processPayout(@RequestBody Map<String, String> request) {
	        try {
	            String username = request.get("username");
	            String name = request.get("name");
	            String email = request.get("email");
	            String phone = request.get("phone");
	            String accountNumber = request.get("accountNumber");
	            String ifsc = request.get("ifsc");

	            String contactId = mockPayoutService.createContact(name, email, phone);
	            String fundAccountId = mockPayoutService.createFundAccount(contactId, name, ifsc, accountNumber);
	            String payoutId = mockPayoutService.initiatePayout(fundAccountId, 50000, "INR", "Mock payout to " + username);

	            return ResponseEntity.ok(Map.of("mockPayoutId", payoutId, "username", username));
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	        }
	    }
	   
}
