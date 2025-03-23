package com.example.learn1.repository;

import com.example.learn1.model.UniversityVehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UniversityVehicleRepository extends MongoRepository<UniversityVehicle, String> {
    Optional<UniversityVehicle> findByVehicleNumber(String vehicleNumber);
    void deleteByVehicleNumber(String vehicleNumber);
}
