package com.example.learn1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Vehicles")
@Data
public class Vehicle {
    @Id
    private String id;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleModelName;
    private List<String> vehicleImages; // Store URLs of images
    private String vehicleOwner;
    private String driverName;
    private String driverMobileNumber;
    private String empNumber;

}
