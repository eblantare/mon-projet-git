/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blt.gestadmin.securities.controllers;

import com.blt.gestadmin.securities.dto.UserListDto;
import com.blt.gestadmin.securities.dto.UtilisateurDto;
import com.blt.gestadmin.securities.entity.Role;
import com.blt.gestadmin.securities.entity.Utilisateur;
import com.blt.gestadmin.securities.repository.RoleRepository;
import com.blt.gestadmin.securities.repository.UtilisateurRepository;
import com.blt.gestadmin.securities.services.UserListService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final UserListService userListService;

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
    //Ressource sans pagination
    @GetMapping("/allUsers")
    public List<UserListDto> getAllUsers(){
        List<Utilisateur> listUser = utilisateurRepository.findAll();
        return userListService.myUserDto(listUser);
    }
    //Ressource avec pagination
    @GetMapping("/getAllUsers")
    public Page<UserListDto> ListUtilisateurs(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id,asc") String[]sort,
        @RequestParam(required=false) String search){
            Sort.Direction direction = sort[1].equalsIgnoreCase("desc")
            ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        Page<Utilisateur> usersPage = utilisateurRepository.findAll(pageable);
        return usersPage.map(userListService::userDto);

        }
    
    
}
