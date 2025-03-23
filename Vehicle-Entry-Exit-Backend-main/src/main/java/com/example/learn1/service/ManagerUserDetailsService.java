package com.example.learn1.service;

import com.example.learn1.model.Manager;
import com.example.learn1.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerUserDetailsService implements UserDetailsService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Manager manager = managerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Manager not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                manager.getEmail(),
                manager.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + manager.getRole().name()))
        );
    }
}