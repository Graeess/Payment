package com.example.demo.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

import java.math.BigDecimal;

@Entity
@Data
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private AppUser user;
    private String amount1;


    public void setAmount(@NotNull String amount) {
        amount1 = amount;
    }
}
