package com.blt.gestadmin.securities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blt.gestadmin.securities.dto.UserListDto;
import com.blt.gestadmin.securities.entity.Utilisateur;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.blt.gestadmin.securities.repository.UtilisateurRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserListService {

        public UserListDto userDto(Utilisateur user){
            return UserListDto.builder()
                        .id(user.getId())
                        .nom(user.getNom())
                        .prenoms(user.getPrenoms())
                        .email(user.getEmail())
                        .telephone(user.getTelephone())
                        .username(user.getUsername())
                        .roles(user.getRoles().stream().map(role -> role.getName())
                                    .collect(Collectors.toList()))
                        .build();
        }

        public List<UserListDto> myUserDto(List<Utilisateur> us){
            return us.stream().map(this::userDto).collect(Collectors.toList());
        }

        private final UtilisateurRepository utilisateurRepository;

        public Page<Utilisateur> getUsers(String search, Pageable pageable) {
            if (search != null && !search.trim().isEmpty()) {
                // Use a different existing method or implement custom query
                return utilisateurRepository.findAll(pageable);
            }
            return utilisateurRepository.findAll(pageable);
        }

}
