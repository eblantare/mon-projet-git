package com.blt.gestadmin.securities.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomSuccessHandler implements AuthenticationSuccessHandler{
    public void onAuthenticationSuccess(HttpServletRequest request,
    HttpServletResponse response, Authentication authentication)
    throws IOException,ServletException{
        
        Collection<? extends  GrantedAuthority>authorities =
        authentication.getAuthorities();
        for(GrantedAuthority authority:authorities){
            if(authority.getAuthority().equals("ROLE_ADMIN")){
                response.sendRedirect("/create-user");
                return;
            }
        }
        response.sendRedirect("/");
    }

}
