package com.example.learn1.repository;

import com.example.learn1.model.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ManagerRepository extends MongoRepository<Manager, String> {
    Optional<Manager> findByEmail(String email);
    boolean existsByEmail(String email);
}
