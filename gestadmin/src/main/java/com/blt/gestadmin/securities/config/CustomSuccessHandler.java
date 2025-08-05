package com.blt.gestadmin.securities.config;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
    HttpServletResponse response, Authentication authentication)
    throws IOException,ServletException{
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = authorities.stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList());
        Map<String,Object>responseBody=Map.of("status","success","roles",roles);

        String json = new ObjectMapper().writeValueAsString(responseBody);
        response.getWriter().write(json);
        response.getWriter().flush();
        response.getWriter().write("{\"message\": \"Login successful\"}");
        System.out.println("HANDLER********************");
    }
}
