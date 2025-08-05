/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blt.gestadmin.securities.services;
import com.blt.gestadmin.securities.dto.LoginRequest;
import com.blt.gestadmin.securities.entity.Utilisateur;
import com.blt.gestadmin.securities.repository.UtilisateurRepository;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
/**
 *
 * @author DELL
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Utilisateur u = utilisateurRepository.findUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Not found"));
            logger.info("PASSWORD VALUE**** "+u.getPassword());
            return new org.springframework.security.core.userdetails.User(
            u.getUsername(),
            u.getPassword(),
            u.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList()
        );
    }
}