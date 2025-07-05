/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blt.gestadmin.securities.controllers;

import com.blt.gestadmin.securities.dto.UtilisateurDto;
import com.blt.gestadmin.securities.entity.Role;
import com.blt.gestadmin.securities.entity.Utilisateur;
import com.blt.gestadmin.securities.repository.RoleRepository;
import com.blt.gestadmin.securities.repository.UtilisateurRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */

@RestController
@RequiredArgsConstructor
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/admin")
public class AdminController {
//    @Autowired
    private final UtilisateurRepository utilisateurRepository;
//    @Autowired
    private final RoleRepository RoleRepository;
    private final PasswordEncoder PasswordEncoder;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody UtilisateurDto utilisateurDto){
      if(utilisateurRepository.existsByUsername(utilisateurDto.getUsername())) 
          return ResponseEntity.badRequest().body("username alredy exists");
       
        Utilisateur u = new Utilisateur();
        u.setUsername(utilisateurDto.getUsername());
        u.setPassword(PasswordEncoder.encode(utilisateurDto.getPassword()));
        u.setEnabled(true);
        List<Role> roles =RoleRepository.findByNameIn(utilisateurDto.getRoles());
        u.setRoles(roles);
        utilisateurRepository.save(u);
        
        return ResponseEntity.ok("User created with success!!");
    }
    
    @GetMapping("/users")
    public List<Utilisateur> getAllUsers(){
        return utilisateurRepository.findAll();
    }

    
    
}
