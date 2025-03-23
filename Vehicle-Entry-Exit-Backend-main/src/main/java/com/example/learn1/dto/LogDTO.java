package com.example.learn1.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogDTO {
    private String logId;
    private String securityGuardId;
    private String vehicleNumber;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private String gateNumber;
}

