package com.example.learn1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Gates")
@Data
public class Gate {
    @Id
    private String id;
    private String gateNumber;
    private String gateName;
}

