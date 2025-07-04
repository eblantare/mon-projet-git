/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blt.gestadmin.securities.repository;

import com.blt.gestadmin.securities.entity.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author DELL
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    
    List<Role> findByNameIn(List<String> names);
    
}
