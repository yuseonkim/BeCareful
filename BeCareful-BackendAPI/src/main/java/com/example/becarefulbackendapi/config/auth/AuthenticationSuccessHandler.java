package com.example.becarefulbackendapi.config.auth;

import com.example.becarefulbackendapi.config.Jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long providerId = principalDetails.getAttribute("id");
        String username = "kakao_" + providerId;
        Map<String, Object> properties = (Map<String, Object>) principalDetails.getAttributes().get("properties");
        String nickname = (String) properties.get("nickname");
        System.out.println("=============nickname========== :"+ nickname);
        String jwt = JwtProvider.create(username, nickname);
        System.out.println(jwt);
        response.addHeader(HttpHeaders.AUTHORIZATION, jwt);
        response.sendRedirect("/loginRedirect?token="+jwt);
    }
}

