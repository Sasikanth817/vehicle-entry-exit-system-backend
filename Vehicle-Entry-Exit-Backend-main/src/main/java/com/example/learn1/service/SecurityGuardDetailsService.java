package com.example.learn1.service;

import com.example.learn1.model.SecurityGuard;
import com.example.learn1.repository.SecurityGuardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityGuardDetailsService implements UserDetailsService {

    @Autowired
    private SecurityGuardRepository securityGuardRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SecurityGuard securityGuard = securityGuardRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Security Guard not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                securityGuard.getEmail(),
                securityGuard.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + securityGuard.getRole().name()))
        );
    }
}