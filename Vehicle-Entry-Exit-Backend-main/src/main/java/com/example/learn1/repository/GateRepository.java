package com.example.learn1.repository;

import com.example.learn1.model.Gate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GateRepository extends MongoRepository<Gate, String> {
        Optional<Gate> findByGateNumber(String gateNumber);
        void deleteByGateNumber(String gateNumber); // Add this method

}