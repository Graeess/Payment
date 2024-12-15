package com.example.demo.Model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class PaymentRequest {
    @NotNull
    private String user;
    @NotNull
    private String amount;
    @NotNull
    private String paymentMethod;
}
