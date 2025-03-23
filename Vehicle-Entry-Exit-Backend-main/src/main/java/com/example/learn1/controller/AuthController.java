package com.example.learn1.controller;

import com.example.learn1.dto.LoginRequest;
import com.example.learn1.dto.LoginResponse;
import com.example.learn1.model.Manager;
import com.example.learn1.model.SecurityGuard;
import com.example.learn1.model.User;
import com.example.learn1.repository.ManagerRepository;
import com.example.learn1.repository.SecurityGuardRepository;
import com.example.learn1.repository.UserRepository;
import com.example.learn1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private SecurityGuardRepository securityGuardRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        System.out.println("Login attempt for email: " + email); // Log the email

        // Check for User
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            System.out.println("User found with email: " + email); // Log user found
            System.out.println("Stored password: " + userOpt.get().getPassword()); //Log stored hash
            if (passwordEncoder.matches(password, userOpt.get().getPassword())) {
                System.out.println("User password match successful"); //log password match
                String token = jwtUtil.generateToken(email, "USER");
                // Get empNumber and email from the User object
                String empNumber = userOpt.get().getEmpNumber();
                String userEmail = userOpt.get().getEmail();

                return ResponseEntity.ok(new LoginResponse("Login Successful", "USER", token, userEmail, empNumber));
            } else {
                System.out.println("User password match failed"); //log password failed match
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        }

        // Check for Manager
        Optional<Manager> managerOpt = managerRepository.findByEmail(email);
        if (managerOpt.isPresent()) {
            System.out.println("Manager found with email: " + email); //Log manager found
            System.out.println("Stored password: " + managerOpt.get().getPassword()); //Log stored hash
            if (passwordEncoder.matches(password, managerOpt.get().getPassword())) {
                System.out.println("Manager password match successful"); //log password match
                String token = jwtUtil.generateToken(email, "MANAGER");
                // Get empNumber and email from the User object
                String empNumber = managerOpt.get().getEmpNumber();
                String userEmail = managerOpt.get().getEmail();
                return ResponseEntity.ok(new LoginResponse("Login Successful", "MANAGER", token,userEmail, empNumber));
            } else {
                System.out.println("Manager password match failed"); //log password failed match
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        }

        // Check for Security Guard
        Optional<SecurityGuard> guardOpt = securityGuardRepository.findByEmail(email);
        if (guardOpt.isPresent()) {
            System.out.println("Security Guard found with email: " + email); //log guard found
            System.out.println("Stored password: " + guardOpt.get().getPassword()); //Log stored hash
            if (passwordEncoder.matches(password, guardOpt.get().getPassword())) {
                System.out.println("Security Guard password match successful"); //log password match
                String token = jwtUtil.generateToken(email, "SECURITY_GUARD");
                // Get empNumber and email from the User object
                String empNumber = guardOpt.get().getEmpNumber();
                String userEmail = guardOpt.get().getEmail();
                return ResponseEntity.ok(new LoginResponse("Login Successful", "SECURITY_GUARD", token,userEmail, empNumber));
            } else {
                System.out.println("Security Guard password match failed"); //log password failed match
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        }

        System.out.println("User not found with email: " + email); //Log user not found
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
}
