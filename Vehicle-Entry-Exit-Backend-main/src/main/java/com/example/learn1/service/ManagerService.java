package com.example.learn1.service;

import com.example.learn1.dto.*;
import com.example.learn1.model.*;
import com.example.learn1.repository.*;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    private SecurityGuardRepository securityGuardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UniversityVehicleRepository universityVehicleRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // GET /security-guards
    public List<SecurityGuard> getAllSecurityGuards() {
        return securityGuardRepository.findAll();
    }

    // GET /security-guards/{securityGuardId}
    public SecurityGuard getSecurityGuard(String securityGuardId) {
        return securityGuardRepository.findBySecurityGuardId(securityGuardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Security Guard not found"));
    }

    // POST /security-guards
    public SecurityGuard addSecurityGuard(SecurityGuardDTO securityGuardDTO) {
        SecurityGuard securityGuard = new SecurityGuard();
        securityGuard.setSecurityGuardId(securityGuardDTO.getSecurityGuardId());
        securityGuard.setFirstName(securityGuardDTO.getFirstName());
        securityGuard.setMiddleName(securityGuardDTO.getMiddleName());
        securityGuard.setLastName(securityGuardDTO.getLastName());
        securityGuard.setEmpNumber(securityGuardDTO.getEmpNumber());
        securityGuard.setEmail(securityGuardDTO.getEmail());
        securityGuard.setContactNumber(securityGuardDTO.getContactNumber());
        securityGuard.setSecurityGuardId(securityGuardDTO.getSecurityGuardId());
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(securityGuardDTO.getPassword());
        securityGuard.setPassword(hashedPassword);

        return securityGuardRepository.save(securityGuard);
    }

    // PUT /security-guards/{securityGuardId}
    public SecurityGuard updateSecurityGuard(String securityGuardId, SecurityGuardDTO securityGuardDTO) {
        SecurityGuard securityGuard = securityGuardRepository.findBySecurityGuardId(securityGuardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Security Guard not found"));

        securityGuard.setFirstName(securityGuardDTO.getFirstName());
        securityGuard.setMiddleName(securityGuardDTO.getMiddleName());
        securityGuard.setLastName(securityGuardDTO.getLastName());
        securityGuard.setEmpNumber(securityGuardDTO.getEmpNumber());
        securityGuard.setEmail(securityGuardDTO.getEmail());
        securityGuard.setContactNumber(securityGuardDTO.getContactNumber());

        return securityGuardRepository.save(securityGuard);
    }

    // DELETE /security-guards/{securityGuardId}
    public void deleteSecurityGuard(String securityGuardId) {
        if (securityGuardRepository.findBySecurityGuardId(securityGuardId).isPresent()) {
            securityGuardRepository.deleteBySecurityGuardId(securityGuardId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Security Guard not found");
        }
    }

    // POST /add-user
//    public User addUser(UserDTO userDTO) {
//        User user = new User();
//        user.setFirstName(userDTO.getFirstName());
//        user.setMiddleName(userDTO.getMiddleName());
//        user.setLastName(userDTO.getLastName());
//        user.setEmpNumber(userDTO.getEmpNumber());
//        user.setEmail(userDTO.getEmail());
//        user.setContactNumber(userDTO.getContactNumber());
//        user.setUserType(userDTO.getUserType());
//        // IMPORTANT: Hash the password before saving!
//        // user.setPassword(hashedPassword);
//        return userRepository.save(user);
//    }

    public User addUser(UserDTO userDTO) {
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

    // DELETE /user/{empNumber}
    public void deleteUser(String empNumber) {
        if (userRepository.findByEmpNumber(empNumber).isPresent()) {
            userRepository.deleteByEmpNumber(empNumber);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    // GET /users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 6) GET /university-vehicles
    public List<UniversityVehicle> getUniversityVehicles() {
        return universityVehicleRepository.findAll();
    }

    // 7) POST /add-university-vehicles
    public UniversityVehicle addUniversityVehicle(UniversityVehicleDTO vehicleDTO) {
        UniversityVehicle vehicle = new UniversityVehicle();
        vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setVehicleModelName(vehicleDTO.getVehicleModelName());
        vehicle.setVehicleImages(vehicleDTO.getVehicleImages());
        vehicle.setDriverName(vehicleDTO.getDriverName());
        vehicle.setDriverMobileNumber(vehicleDTO.getDriverMobileNumber());

        return universityVehicleRepository.save(vehicle);
    }

    // 8) DELETE /delete-university-vehicles/{vehicleNumber}
    public void deleteUniversityVehicle(String vehicleNumber) {
        Optional<UniversityVehicle> vehicle = universityVehicleRepository.findByVehicleNumber(vehicleNumber);
        if (vehicle.isPresent()) {
            universityVehicleRepository.deleteByVehicleNumber(vehicleNumber);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "University vehicle not found");
        }
    }

    // 9) PUT /university-vehicles/{vehicleNumber}
    public UniversityVehicle editUniversityVehicle(String vehicleNumber, UniversityVehicleDTO vehicleDTO) {
        Optional<UniversityVehicle> optionalVehicle = universityVehicleRepository.findByVehicleNumber(vehicleNumber);
        if (optionalVehicle.isPresent()) {
            UniversityVehicle vehicle = optionalVehicle.get();
            vehicle.setVehicleType(vehicleDTO.getVehicleType());
            vehicle.setVehicleModelName(vehicleDTO.getVehicleModelName());
            vehicle.setVehicleImages(vehicleDTO.getVehicleImages());
            vehicle.setDriverName(vehicleDTO.getDriverName());
            vehicle.setDriverMobileNumber(vehicleDTO.getDriverMobileNumber());

            return universityVehicleRepository.save(vehicle);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "University vehicle not found");
        }
    }

    // 10) GET /logs
    public List<Log> getLogs() {
        return logRepository.findAll();
    }

//     11) POST /add-gates
    public Gate addGates(GateDTO gateDTO) {
        Gate gate = new Gate();
        gate.setGateNumber(gateDTO.getGateNumber());
        gate.setGateName(gateDTO.getGateName());
        return gateRepository.save(gate);
    }

    // 11) GET /gates
    public List<Gate> getGates() {
        return gateRepository.findAll();
    }

    // 12) DELETE /gates/{gateNumber}
    public void deleteGates(String gateNumber) {
        Optional<Gate> gate = gateRepository.findByGateNumber(gateNumber);
        if (gate.isPresent()) {
            gateRepository.deleteByGateNumber(gateNumber);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gate not found");
        }
    }

    // 13) POST /announcements
    public Announcement addAnnouncement(AnnouncementDTO announcementDTO) {
        Announcement announcement = new Announcement();
        announcement.setId(announcementDTO.getId());
        announcement.setTitle(announcementDTO.getTitle());
        announcement.setDescription(announcementDTO.getDescription());
        return announcementRepository.save(announcement);
    }

    // 14) DELETE /announcements/{announcementId}
    public void deleteAnnouncement(String announcementId) {
        if (announcementRepository.existsById(announcementId)) {
            announcementRepository.deleteById(announcementId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement not found");
        }
    }

    // 15) GET /announcements
    public List<Announcement> getAnnouncements() {
        return announcementRepository.findAll();
    }

    // GET /user-vehicles/{userID}
    public List<Vehicle> getUserVehicles(String empNumber) {
        List<Vehicle> vehicles = vehicleRepository.findByEmpNumber(empNumber);
        if (vehicles.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No vehicles found for empNumber: " + empNumber);
        }
        return vehicles;
    }

    // GET /vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

        // 1) Register Manager
    public Manager registerManager(ManagerDTO managerDTO) {
        Manager manager = new Manager();
        manager.setFirstName(managerDTO.getFirstName());
        manager.setMiddleName(managerDTO.getMiddleName());
        manager.setLastName(managerDTO.getLastName());
        manager.setEmpNumber(managerDTO.getEmpNumber());
        manager.setEmail(managerDTO.getEmail());
        manager.setContactNumber(managerDTO.getContactNumber());

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(managerDTO.getPassword());
        manager.setPassword(hashedPassword);  // Set the hashed password

        return managerRepository.save(manager);
    }
}