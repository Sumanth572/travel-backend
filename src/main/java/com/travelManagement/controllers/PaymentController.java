package com.travelManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelManagement.model.Payment;
import com.travelManagement.services.PaymentService;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @PostMapping("/pay")
    public Payment makePayment(@RequestBody Payment payment) {
        return paymentService.makePayment(payment.getBooking().getId(), payment);
    }

    @DeleteMapping("/delete")
    public void deletePayment(@RequestBody Payment payment) {
        paymentService.deletePayment(payment.getId());
    }

    @GetMapping("/ping")
    public String ping() {
        return "payments pong";
    }
}
