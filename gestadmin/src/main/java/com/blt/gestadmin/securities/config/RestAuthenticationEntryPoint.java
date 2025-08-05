package com.blt.gestadmin.securities.config;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest request,HttpServletResponse response,
    AuthenticationException authException) throws IOException{
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        if("application/json".equals(request.getHeader("Accept"))){
                    response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = new ObjectMapper().writeValueAsString(
                Map.of("status", "error", "message", "Non connecté")
        );
        response.getWriter().print(json);
        response.getWriter().flush();
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Non connecté");
        }


    }

}
