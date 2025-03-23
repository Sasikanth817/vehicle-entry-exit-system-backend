package com.example.learn1.repository;


import com.example.learn1.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogRepository extends MongoRepository<Log, String> {
    List<Log> findByVehicleNumber(String vehicleNumber);
    List<Log> findBySecurityGuardId(String securityGuardId);
}

