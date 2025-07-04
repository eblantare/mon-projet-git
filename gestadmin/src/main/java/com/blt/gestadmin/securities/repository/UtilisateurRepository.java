/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blt.gestadmin.securities.repository;

import com.blt.gestadmin.securities.entity.Utilisateur;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author DELL
 */
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
    Optional<Utilisateur> findUserByUsername(String username);
    boolean existsByUsername(String username);
    
}
