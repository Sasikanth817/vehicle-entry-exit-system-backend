package com.example.learn1.controller;

import com.example.learn1.dto.*;
import com.example.learn1.model.*;
import com.example.learn1.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    // 1) GET /security-guards
    @GetMapping("/security-guards")
    public List<SecurityGuard> getGuards() {
        return managerService.getAllSecurityGuards();
    }

    // GET /security-guards/{securityGuardId}
    @GetMapping("/security-guards/{securityGuardId}")
    public SecurityGuard getSecurityGuard(@PathVariable String securityGuardId) {
        return managerService.getSecurityGuard(securityGuardId);
    }

    // POST /security-guards
    @PostMapping("/security-guards")
    public SecurityGuard addSecurityGuard(@RequestBody SecurityGuardDTO securityGuardDTO) {
        return managerService.addSecurityGuard(securityGuardDTO);
    }

    // PUT /security-guards/{securityGuardId}
    @PutMapping("/security-guards/{securityGuardId}")
    public SecurityGuard updateSecurityGuard(@PathVariable String securityGuardId, @RequestBody SecurityGuardDTO securityGuardDTO) {
        return managerService.updateSecurityGuard(securityGuardId, securityGuardDTO);
    }

    // DELETE /security-guards/{securityGuardId}
    @DeleteMapping("/{securityGuardId}")
    public void deleteSecurityGuard(@PathVariable String securityGuardId) {
        managerService.deleteSecurityGuard(securityGuardId);
    }

    // POST /add-user
    @PostMapping("/add-user")
    public User addUser(@RequestBody UserDTO userDTO) {
        return managerService.addUser(userDTO);
    }

    // DELETE /user/{empNumber}
    @DeleteMapping("/user/{empNumber}")
    public void deleteUser(@PathVariable String empNumber) {
        managerService.deleteUser(empNumber);
    }

    // GET /users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return managerService.getAllUsers();
    }


    // 6) GET /university-vehicles
    @GetMapping("/university-vehicles")
    public List<UniversityVehicle> getUniversityVehicles() {
        return managerService.getUniversityVehicles();
    }

    // 7) POST /add-university-vehicles
    @PostMapping("/add-university-vehicles")
    public UniversityVehicle addUniversityVehicle(@RequestBody UniversityVehicleDTO vehicleDTO) {
        return managerService.addUniversityVehicle(vehicleDTO);
    }

    // 8) DELETE /delete-university-vehicles/{vehicleNumber}
    @DeleteMapping("/delete-university-vehicles/{vehicleNumber}")
    public void deleteUniversityVehicle(@PathVariable String vehicleNumber) {
        managerService.deleteUniversityVehicle(vehicleNumber);
    }

    // 9) PUT /university-vehicles/{vehicleNumber}
    @PutMapping("/university-vehicles/{vehicleNumber}")
    public UniversityVehicle editUniversityVehicle(@PathVariable String vehicleNumber, @RequestBody UniversityVehicleDTO vehicleDTO) {
        return managerService.editUniversityVehicle(vehicleNumber, vehicleDTO);
    }

    // 10) GET /logs
    @GetMapping("/logs")
    public List<Log> getLogs() {
        return managerService.getLogs();
    }

    //11) POST /add-gates
    @PostMapping("/add-gates")
    public Gate addGates(@RequestBody GateDTO gateDTO) {
        return managerService.addGates(gateDTO);
    }

    //GET /gates
    @GetMapping("/gates")
    public List<Gate> getGates() {
        return managerService.getGates();
    }

    // 12) DELETE /gates/{gateNumber}
    @DeleteMapping("/gates/{gateNumber}")
    public void deleteGates(@PathVariable String gateNumber) {
        managerService.deleteGates(gateNumber);
    }

    // 13) POST /announcements
    @PostMapping("/announcements")
    public Announcement addAnnouncement(@RequestBody AnnouncementDTO announcementDTO) {
        return managerService.addAnnouncement(announcementDTO);
    }

    // 14) DELETE /announcements/{announcementId}
    @DeleteMapping("/announcements/{announcementId}")
    public void deleteAnnouncement(@PathVariable String announcementId) {
        managerService.deleteAnnouncement(announcementId);
    }

    // 15) GET /announcements
    @GetMapping("/announcements")
    public List<Announcement> getAnnouncements() {
        return managerService.getAnnouncements();
    }

    // GET /user-vehicles/{userID}
    @GetMapping("/user-vehicles/{userID}")
    public List<Vehicle> getUserVehicles(@PathVariable String userID) {
        return managerService.getUserVehicles(userID);
    }

    // GET /vehicles
    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return managerService.getAllVehicles();
    }

        // 1) POST /manager
    @PostMapping("/manager")
    public Manager registerManager(@RequestBody ManagerDTO managerDTO) {
        return managerService.registerManager(managerDTO);
    } //Temporary


}