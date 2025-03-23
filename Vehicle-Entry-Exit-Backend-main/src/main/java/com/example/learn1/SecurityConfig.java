package com.example.learn1;

import com.example.learn1.filter.JwtAuthenticationFilter;
import com.example.learn1.service.CustomUserDetailsService;
import com.example.learn1.service.ManagerUserDetailsService;
import com.example.learn1.service.SecurityGuardDetailsService;
import com.example.learn1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private ManagerUserDetailsService managerUserDetailsService;

    @Autowired
    private SecurityGuardDetailsService securityGuardDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/register-user", "/api/auth/login","/api/manager/manager").permitAll() // Open to all //Need to remove permisiion to add manager later
                        .requestMatchers("/api/security-guard/**").hasRole("SECURITY_GUARD") // Security Guard only
                        .requestMatchers("/api/manager/**").hasRole("MANAGER") // Manager only
                        .requestMatchers("/api/**").hasRole("USER") // User only
                        .anyRequest().authenticated() // All other requests require authentication
                )
//                .formLogin(withDefaults()) // Default login form
//                .httpBasic(withDefaults()); // Enable Basic Authentication
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, customUserDetailsService,
                        managerUserDetailsService,
                        securityGuardDetailsService), UsernamePasswordAuthenticationFilter.class); // Add JWT filter
        return http.build();

    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}