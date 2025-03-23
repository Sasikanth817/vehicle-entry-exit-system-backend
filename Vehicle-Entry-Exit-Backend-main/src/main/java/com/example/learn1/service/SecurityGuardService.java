// SecurityGuardService.java

package com.example.learn1.service;

//import com.example.learn1.dto.BarcodeScanDTO;
import com.example.learn1.dto.AnnouncementDTO;
import com.example.learn1.dto.LogDTO;
import com.example.learn1.model.*;
import com.example.learn1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SecurityGuardService {

    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private SecurityGuardRepository securityGuardRepository;

    // 1) GET /gates
    public List<Gate> getGates() {
        return gateRepository.findAll();
    }

    // 2) GET /vehicles/{vehicleNumber}
    public Vehicle getVehicleData(String vehicleNumber) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);

        if (optionalVehicle.isPresent()) {
            return optionalVehicle.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
        }
    }

    // 3) GET /announcements
    public List<AnnouncementDTO> getAnnouncements() {
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

    // 4) GET /logs
    public List<Log> getLogsBySecurityGuardId(String securityGuardId) {
        List<Log> logs = logRepository.findBySecurityGuardId(securityGuardId);
        if (logs.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No logs found for security guard id " + securityGuardId);
        }
        return logs;
    }

    // 5) POST /log
    public Log postLog(LogDTO logDTO) {
        Log log = new Log();
        System.out.println("Received LogDTO: " + logDTO.toString());
        log.setVehicleNumber(logDTO.getVehicleNumber());
        log.setGateNumber(logDTO.getGateNumber());
        log.setSecurityGuardId(logDTO.getSecurityGuardId());
        log.setTimeIn(logDTO.getTimeIn());
        log.setTimeOut(logDTO.getTimeOut());
        System.out.println("Log Entity: " + log.toString());
        return logRepository.save(log);
    }

    // 10) GET /logs
    public List<Log> getLogs() {
        return logRepository.findAll();
    }

    //GET by Email id
    public Optional<SecurityGuard> getSecurityGuardByEmail(String email) {
        return securityGuardRepository.findByEmail(email);
    }
}