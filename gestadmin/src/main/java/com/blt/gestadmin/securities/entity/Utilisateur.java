/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blt.gestadmin.securities.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Entity
@Table(name ="sec_users")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Utilisateur {
    @Id @GeneratedValue
    @Column(name="user_id")
    private Long id;
    @Column(name="user_name")
    private String nom;
    @Column(name="user_surname")
    private String prenoms;
    @Email(message = "Adresse email invalide")
    @NotBlank(message = "L'e-mail est obligatoire")
    @Column(name="user_email",nullable = false, unique = true)
    private String email;
    @Pattern(regexp = "^\\+?[0-9]{10,15}$",message = "numéro de téléphone invalide")
    @Column(name="user_telephone")
    private String telephone;
    @Column(name="user_username",unique =true)
    // @Size(min = 8, message = "Username doit avoir au moins 8 caractères")
    private String username;
    @Column(name="user_password",unique =true)
    @Size(min = 8, message = "Password doit avoir au moins 8 caractères")
    private String password;
    private boolean enabled=true;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="sec_users_roles", joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;
            
    
}
