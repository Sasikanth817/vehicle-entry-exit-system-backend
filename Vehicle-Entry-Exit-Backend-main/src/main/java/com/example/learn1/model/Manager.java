package com.example.learn1.model;

import com.example.learn1.enums.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Managers")
@Data
public class Manager {
    @Id
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String empNumber;
    private String email;
    private String password;
    private String contactNumber;
    private Role role = Role.MANAGER;
}
