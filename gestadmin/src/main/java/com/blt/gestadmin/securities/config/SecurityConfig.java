/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blt.gestadmin.securities.config;

import com.blt.gestadmin.securities.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author DELL
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.csrf(csrf ->csrf.disable())
             .authorizeHttpRequests(auth ->auth
             .requestMatchers("login","/forgot-password", "/reset-password").permitAll()
             .requestMatchers("/admin/**").hasRole("ADMIN")
             .anyRequest().authenticated()
             )
                .formLogin(form->form.loginPage("/login").permitAll())
              
                .logout(logi->logi.logoutUrl("/logout").logoutSuccessUrl("/login?logout")
                );
         return  http.build();
    }
    
   
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
