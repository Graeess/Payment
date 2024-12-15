package com.example.demo.Repository;
import com.example.demo.Entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<AppRole, Long> {
    void findByRoleName(String roleName);
}
