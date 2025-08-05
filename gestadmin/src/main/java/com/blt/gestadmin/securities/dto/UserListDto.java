package com.blt.gestadmin.securities.dto;

import java.util.List;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserListDto {
    private Long id;
    private String nom;
    private String prenoms;
    private String email;
    private String telephone;
    private String username;
    private List<String> roles;
    private String photo;

}
