package com.example.learn1.controller;


//import com.example.learn1.dto.BarcodeScanDTO;
import com.example.learn1.dto.AnnouncementDTO;
import com.example.learn1.dto.LogDTO;
import com.example.learn1.model.Gate;
import com.example.learn1.model.Log;
import com.example.learn1.model.SecurityGuard;
import com.example.learn1.model.Vehicle;
import com.example.learn1.service.SecurityGuardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/security-guard")
public class SecurityGuardController {

    @Autowired
    private SecurityGuardService securityGuardService;

    // 1) GET /gates
    @GetMapping("/gates")
    public List<String> getGates() {
        return securityGuardService.getGates().stream()
                .map(Gate::getGateNumber)
                .collect(Collectors.toList());
    }

    // 2) GET /vehicles/{vehicleNumber}
    @GetMapping("/vehicles/{vehicleNumber}")
    public Vehicle getVehicleData(@PathVariable String vehicleNumber) {
        return securityGuardService.getVehicleData(vehicleNumber);
    }

    // 3) GET /announcements
    @GetMapping("/announcements")
    public List<AnnouncementDTO> getAllAnnouncements() {
        return securityGuardService.getAnnouncements();
    }

    // 4) GET /logs
    @GetMapping("/logs/{securityGuardId}")
    public List<Log> getLogsBySecurityGuardId(@PathVariable String securityGuardId) {
        return securityGuardService.getLogsBySecurityGuardId(securityGuardId);
    }

    // 5) POST /log
    @PostMapping("/logs")
    public Log postLog(@RequestBody LogDTO logDTO) {
        return securityGuardService.postLog(logDTO);
    }

    // 10) GET /logs
    @GetMapping("/logs")
    public List<Log> getLogs() {
        return securityGuardService.getLogs();
    }

    @GetMapping("/profile/email/{email}")
    public Optional<SecurityGuard> getSecurityGuardByEmail(@PathVariable String email) {
        return securityGuardService.getSecurityGuardByEmail(email);
    }

}