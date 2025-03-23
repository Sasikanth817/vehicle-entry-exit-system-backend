package com.example.learn1.filter;

import com.example.learn1.service.CustomUserDetailsService;
import com.example.learn1.service.ManagerUserDetailsService;
import com.example.learn1.service.SecurityGuardDetailsService;
import com.example.learn1.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final ManagerUserDetailsService managerUserDetailsService;
    private final SecurityGuardDetailsService securityGuardDetailsService;

//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Autowired
//    private ManagerUserDetailsService managerUserDetailsService;
//
//    @Autowired
//    private SecurityGuardDetailsService securityGuardDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService,
                                   ManagerUserDetailsService managerUserDetailsService,
                                   SecurityGuardDetailsService securityGuardDetailsService) {
        this.jwtUtil = jwtUtil;
//        this.userDetailsService = userDetailsService;
        this.customUserDetailsService = customUserDetailsService;
        this.managerUserDetailsService = managerUserDetailsService;
        this.securityGuardDetailsService = securityGuardDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                String email = jwtUtil.extractEmail(token);
                String role = jwtUtil.extractRole(token);
                System.out.println("Extracted Role: " + role);
                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetailsService userDetailsService = getUserDetailsService(role);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    if (jwtUtil.validateToken(token, email)) {
                        List<GrantedAuthority> authorities = new ArrayList<>();
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error during JWT validation: " + e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }


    private UserDetailsService getUserDetailsService(String role) {
        System.out.println("getUserDetailsService called with role: " + role);
        UserDetailsService service;
        switch (role) {
            case "MANAGER":
                service = managerUserDetailsService;

                break;
            case "SECURITY_GUARD":
                service = securityGuardDetailsService;
                break;
            default:
                service = customUserDetailsService; // Default to CustomUserDetailsService for other roles
                break;
        }
        System.out.println("getUserDetailsService returning: " + service);
        return service;
    }
}

//    private UserDetailsService getUserDetailsService(String role) {
//        switch (role) {
//            case "MANAGER":
//                return managerUserDetailsService;
//            case "SECURITY_GUARD":
//                return securityGuardDetailsService;
//            default:
//                return customUserDetailsService; // Default to CustomUserDetailsService for other roles
//        }
//    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(7);
//            try {
//                String email = jwtUtil.extractEmail(token);
//                String role = jwtUtil.extractRole(token);
//                System.out.println("Extracted Role: " + role);
//                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//                    if (jwtUtil.validateToken(token, email)) {
//                        List<GrantedAuthority> authorities = new ArrayList<>();
//                        authorities.add(new SimpleGrantedAuthority("ROLE_" + role)); // Correct: Add role from JWT
//                        UsernamePasswordAuthenticationToken authenticationToken =
//                                new UsernamePasswordAuthenticationToken(userDetails, null, authorities); // Correct: Use generated authorities
//                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println("Error during JWT validation: " + e.getMessage());
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(7);
//            try {
//                String email = jwtUtil.extractEmail(token);
//                String role = jwtUtil.extractRole(token); // Extract role
//                System.out.println("Extracted Role: " + role); // Log role
//                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//                    if (jwtUtil.validateToken(token, email)) {
//                        UsernamePasswordAuthenticationToken authenticationToken =
//                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                    }
//                }
//            } catch (Exception e) {
//                // Handle token validation errors
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}


//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(7);
//            try {
//                String username = jwtUtil.extractUsername(token);
//                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                    if (jwtUtil.validateToken(token, userDetails.getUsername())) {
//                        UsernamePasswordAuthenticationToken authenticationToken =
//                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                    }
//                }
//
//            } catch (Exception e) {
//                // Handle token validation errors
//            }
//        }
//        filterChain.doFilter(request, response);
//    }