/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blt.gestadmin.securities.repository;

import com.blt.gestadmin.securities.entity.Utilisateur;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author DELL
 */
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findUserByUsername(String username);
    boolean existsByUsername(String username);

    @Query("""
          SELECT DISTINCT u FROM Utilisateur u
          LEFT JOIN u.roles r WHERE LOWER(u.nom) LIKE LOWER(CONCAT('%',:search,'%'))
          OR LOWER(u.prenoms) LIKE LOWER(CONCAT('%',:search,'%'))
          OR LOWER(u.username) LIKE LOWER(CONCAT('%',:search,'%'))
          OR LOWER(u.telephone) LIKE LOWER(CONCAT('%',:search,'%'))
          OR LOWER(u.email) LIKE LOWER(CONCAT('%',:search,'%'))
          OR LOWER(r.name) LIKE LOWER(CONCAT('%',:search,'%')) """)
    Page<Utilisateur> searchUsers(@Param("search") String search, Pageable pageable);

    Page<Utilisateur> findByUsernameContainingIgnoreCase(String search, Pageable pageable);
}