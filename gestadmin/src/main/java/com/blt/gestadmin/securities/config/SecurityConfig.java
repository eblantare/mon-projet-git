/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blt.gestadmin.securities.config;

import com.blt.gestadmin.securities.services.CustomUserDetailsService;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.Customizer;

/**
 *
 * @author DELL
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private CustomSuccessHandler successHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
        .cors()
        .and()
        .csrf(csrf ->csrf.disable())
             .authorizeHttpRequests(auth ->auth
             .requestMatchers("/api/login","/api/logout","/api/forgot-password", "/api/reset-password").permitAll()
             .requestMatchers("/admin/**").hasRole("ADMIN")
             .anyRequest().authenticated()
             )
             .formLogin(form -> form
            .loginProcessingUrl("/api/login") // IMPORTANT
            .successHandler((req, res, auth) -> res.setStatus(200))
            .failureHandler((req, res, exp) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
            .permitAll()
        ).httpBasic(basic ->basic.disable())
        .logout(logout -> logout
            .logoutUrl("/api/logout")
            .logoutSuccessHandler((req, res, auth) -> res.setStatus(200))
            .permitAll()
        )
        .sessionManagement(sess -> sess
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        );
        return http.build();
    }
    
   
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:4200"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("/*"));
    configuration.setAllowCredentials(true);
    

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
    return config.getAuthenticationManager();
}
}
