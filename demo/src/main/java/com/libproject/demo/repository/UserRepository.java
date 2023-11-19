package com.libproject.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libproject.demo.domain.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByName(String name);
    Optional<User> findByEmail(String email);
}
