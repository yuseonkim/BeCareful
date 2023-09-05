package com.example.becarefulbackendapi.repository;

import com.example.becarefulbackendapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);
}
