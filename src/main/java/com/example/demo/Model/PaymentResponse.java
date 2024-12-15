package com.example.demo.Model;

import lombok.Data;

@Data
@XmlRootElement
public class PaymentResponse {

    private final String success;
    private final String checkRequestProcessedSuccessfully;
    private String status;
    private String message;

    public PaymentResponse(String success, String checkRequestProcessedSuccessfully) {
        this.success = success;
        this.checkRequestProcessedSuccessfully = checkRequestProcessedSuccessfully;
    }
}