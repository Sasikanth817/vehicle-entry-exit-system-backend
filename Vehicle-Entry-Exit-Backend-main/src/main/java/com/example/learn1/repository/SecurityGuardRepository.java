package com.example.learn1.repository;

import com.example.learn1.model.SecurityGuard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SecurityGuardRepository extends MongoRepository<SecurityGuard, String> {
    Optional<SecurityGuard> findBySecurityGuardId(String securityGuardId);
    void deleteBySecurityGuardId(String securityGuardId);
    Optional<SecurityGuard> findByEmail(String email);
    boolean existsByEmail(String email);

}