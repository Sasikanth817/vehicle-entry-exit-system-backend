package com.example.learn1.service;

import com.example.learn1.dto.AnnouncementDTO;
import com.example.learn1.dto.UserDTO;
import com.example.learn1.model.*;
import com.example.learn1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 1) Register User
    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        user.setEmpNumber(userDTO.getEmpNumber());
        user.setEmail(userDTO.getEmail());
        user.setContactNumber(userDTO.getContactNumber());
        user.setUserType(userDTO.getUserType());

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(hashedPassword);  // Set the hashed password

        return userRepository.save(user);
    }


    // 5) Get User Logs
    public List<Log> getUserVehicleLogs(String empNumber) {
        List<Vehicle> vehicles = vehicleRepository.findByEmpNumber(empNumber);

        if (vehicles.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No vehicles found for empNumber: " + empNumber);
        }

        List<Log> allLogs = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            List<Log> logs = logRepository.findByVehicleNumber(vehicle.getVehicleNumber());
            allLogs.addAll(logs);
        }

        if (allLogs.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No logs found for vehicles of empNumber: " + empNumber);
        }

        return allLogs;
    }

    // 6) Get Announcements
    public List<AnnouncementDTO> getAllAnnouncements1() {
        List<Announcement> announcements = announcementRepository.findAll();
        return announcements.stream()
                .map(announcement -> {
                    AnnouncementDTO dto = new AnnouncementDTO();
                    dto.setId(announcement.getId());
                    dto.setTitle(announcement.getTitle());
                    dto.setDescription(announcement.getDescription());
                    dto.setDate(announcement.getDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    //GET by Email id
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
