package com.example.demo.Controller;

import com.example.demo.Model.PaymentRequest;
import com.example.demo.Model.PaymentResponse;
import com.example.demo.Service.PaymentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Data
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private PaymentRequest paymentRequest;

    @PostMapping(value = "/pay", consumes = "application/json", produces = "application/xml")
    public PaymentResponse handlePayRequest(@RequestBody PaymentRequest paymentRequest) {
        paymentService.savePayment(paymentRequest);
        return new PaymentResponse("Success", "Payment processed successfully");
    }

    @PostMapping(value = "/check", consumes = "application/json", produces = "application/xml")
    protected PaymentResponse handleCheckRequest(@RequestBody PaymentRequest paymentRequest) {
        this.paymentRequest = paymentRequest;

        return new PaymentResponse("Success", "Check request processed successfully");
    }
}