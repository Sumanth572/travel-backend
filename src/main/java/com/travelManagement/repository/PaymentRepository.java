package com.travelManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelManagement.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
