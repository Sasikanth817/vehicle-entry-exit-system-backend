package com.example.learn1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Announcements")
@Data
public class Announcement {
    @Id
    private String id;
    private String title;
    private String description;
    private String date;
}
