package com.blt.gestadmin.securities.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
            if(authentication == null)
                {return ResponseEntity.status(401).body("Non connecté");
            }
            var user = authentication.getPrincipal();
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
    

}
