package com.example.demo.Entity;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private AppRole role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Payments> payments;



}

