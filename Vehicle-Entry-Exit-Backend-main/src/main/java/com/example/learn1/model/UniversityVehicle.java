package com.example.learn1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "UniversityVehicles")
@Data
public class UniversityVehicle {
    @Id
    private String id;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleModelName;
    private List<String> vehicleImages; // Store URLs of images
    private String driverName;
    private String driverMobileNumber;
}
