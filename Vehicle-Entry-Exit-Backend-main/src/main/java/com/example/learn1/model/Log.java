package com.example.learn1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Logs")
@Data
public class Log {
    @Id
    private String logId;
    private String securityGuardId;
    private String vehicleNumber;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private String gateNumber;
}
