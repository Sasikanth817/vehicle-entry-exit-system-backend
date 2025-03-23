package com.example.learn1.dto;

import lombok.Data;
import java.util.List;

@Data
public class UniversityVehicleDTO {
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleModelName;
    private List<String> vehicleImages;
    private String driverName;
    private String driverMobileNumber;
}