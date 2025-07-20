package com.blt.gestadmin.securities.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;
import com.blt.gestadmin.securities.dto.LoginRequest;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
private AuthenticationManager authenticationManager;
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
            if(authentication == null || !authentication.isAuthenticated())
                {return ResponseEntity.status(401).body("Non connecté");
            }
            Object user = authentication.getPrincipal();
            if(user instanceof UserDetails u){
                List<String> roles = u.getAuthorities()
                                .stream().map(a -> a.getAuthority())
                                .toList();
                Map<String,Object>res = new HashMap<>();
                res.put("username", u.getUsername());
                res.put("roles", roles);
                return ResponseEntity.ok(res);
            }
           return ResponseEntity.status(403).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?>logout(HttpServletRequest request){
        request.getSession().invalidate();
        return ResponseEntity.ok("Logged out");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(
    @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        System.out.println("BIENVENUE DANS LE LOGIN");
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
        try {
            Authentication auth = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            // Crée la session HTTP (et donc le JSESSIONID)
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            return ResponseEntity.ok().body(Map.of("message","Login successful"));
            }
             catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: bad credentials");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed: server error");
        }
  }
}