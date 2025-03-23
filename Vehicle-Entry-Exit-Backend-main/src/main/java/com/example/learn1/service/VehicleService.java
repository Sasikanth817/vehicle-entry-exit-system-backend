package com.example.learn1.service;

import com.example.learn1.dto.VehicleDTO;
import com.example.learn1.model.SecurityGuard;
import com.example.learn1.model.Vehicle;
import com.example.learn1.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // 2) Add Vehicle
    public Vehicle addVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setVehicleModelName(vehicleDTO.getVehicleModelName());
        vehicle.setVehicleImages(vehicleDTO.getVehicleImages());
        vehicle.setVehicleOwner(vehicleDTO.getVehicleOwner());
        vehicle.setDriverName(vehicleDTO.getDriverName());
        vehicle.setDriverMobileNumber(vehicleDTO.getDriverMobileNumber());
        vehicle.setEmpNumber(vehicleDTO.getEmpNumber());
        return vehicleRepository.save(vehicle);
    }

    // 3) Delete Vehicle
    public void deleteVehicle(String vehicleNumber) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicleRepository.deleteById(vehicle.getId()); // Delete by id
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle with vehicleNumber " + vehicleNumber + " not found");
        }
    }

    public Vehicle editVehicle(String vehicleNumber, VehicleDTO vehicleDTO) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber); // Find by vehicleNumber
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicle.setVehicleType(vehicleDTO.getVehicleType());
            vehicle.setVehicleModelName(vehicleDTO.getVehicleModelName());
            vehicle.setVehicleImages(vehicleDTO.getVehicleImages());
            vehicle.setVehicleOwner(vehicleDTO.getVehicleOwner());
            vehicle.setDriverName(vehicleDTO.getDriverName());
            vehicle.setEmpNumber(vehicleDTO.getEmpNumber());
            vehicle.setDriverMobileNumber(vehicleDTO.getDriverMobileNumber());

            return vehicleRepository.save(vehicle); // Save the updated Vehicle
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle with vehicleNumber " + vehicleNumber + " not found");
        }
    }

    // 7) Get Vehicle
    public List<Vehicle> getVehiclesByEmpNumber(String empNumber){
        return vehicleRepository.findByEmpNumber(empNumber);
    }

    //Get Vehicle data by vehicle number
    public Vehicle getVehicleByVehicleNo(String vehicleNumber){
        return vehicleRepository.findByVehicleNumber(vehicleNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"));
    }
}

//// GET /security-guards/{securityGuardId}
//public SecurityGuard getSecurityGuard(String securityGuardId) {
//    return securityGuardRepository.findBySecurityGuardId(securityGuardId)
//            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Security Guard not found"));
//}