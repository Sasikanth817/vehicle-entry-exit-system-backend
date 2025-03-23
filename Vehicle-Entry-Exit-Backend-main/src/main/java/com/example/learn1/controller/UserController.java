package com.example.learn1.controller;

import com.example.learn1.dto.AnnouncementDTO;
import com.example.learn1.dto.ManagerDTO;
import com.example.learn1.dto.UserDTO;
import com.example.learn1.dto.VehicleDTO;
import com.example.learn1.model.*;
import com.example.learn1.service.UserService;
import com.example.learn1.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;


    // 1) POST /register-user
    @PostMapping("/register-user")
    public User registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    // 2) POST /add-vehicle
    @PostMapping("/add-vehicle")
    public Vehicle addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.addVehicle(vehicleDTO);
    }

    // 3) DELETE /delete-vehicle
    @DeleteMapping("/delete-vehicle/{vehicleNumber}")
    public void deleteVehicle(@PathVariable String vehicleNumber) {
        vehicleService.deleteVehicle(vehicleNumber);
    }

    // 4) PUT /edit-vehicle
    @PutMapping("/edit-vehicle/{vehicleNumber}")
    public Vehicle editVehicle(@PathVariable String vehicleNumber, @RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.editVehicle(vehicleNumber, vehicleDTO);
    }

    // 5) GET /user-logs/:empNumber
    @GetMapping("/user-vehicle-logs/{empNumber}") // Changed endpoint name
    public List<Log> getUserVehicleLogs(@PathVariable String empNumber) { // Changed method name
        return userService.getUserVehicleLogs(empNumber);
    }

    // 6) GET /announcements
    @GetMapping("/announcements")
    public List<AnnouncementDTO> getAllAnnouncements1() {
        return userService.getAllAnnouncements1();
    }

    // 7) GET /vehicles/{empNumber}
    @GetMapping("/vehicles/{empNumber}")
    public List<Vehicle> getUserVehicles(@PathVariable String empNumber) {
        return vehicleService.getVehiclesByEmpNumber(empNumber);
    }

    @GetMapping("/profile/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
}

