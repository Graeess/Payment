package com.example.demo.Repository;
import com.example.demo.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);

    boolean existsByUsername(String username);
}


