/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blt.gestadmin.securities.dto;

import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DELL
 */
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UtilisateurDto {
    private Long id;
    @Size(min = 8)
    private String username;
    @Size(min = 8)
    private String password;
    private List<String> roles;
    
    
}
