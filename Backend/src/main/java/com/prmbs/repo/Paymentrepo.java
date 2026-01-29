package com.prmbs.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prmbs.dao.PaymentRecord;

public interface Paymentrepo extends JpaRepository<PaymentRecord, Long> {
    Optional<PaymentRecord> findByOrderId(String orderId);
}
