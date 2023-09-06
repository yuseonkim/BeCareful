package com.example.becarefulbackendapi.controller;

import com.example.becarefulbackendapi.config.Jwt.JwtProvider;
import com.example.becarefulbackendapi.config.auth.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@RestController
public class AIController {
    private final RestTemplate restTemplate;

    @Autowired
    public AIController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



//    @GetMapping("/")
//    public ResponseEntity<?> getDataFromExternalService(Authentication authentication) {
//        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//        Long providerId = principalDetails.getAttribute("id");
//        String username = "kakao"+providerId;
//        Map<String,Object> properties = (Map<String, Object>) principalDetails.getAttributes().get("properties");
//        String nickname = (String) properties.get("nickname");
//        String jwt = JwtProvider.create(username,nickname);
//        String externalServerUrl = "http://localhost:8070/main"; // 외부 서버의 URL
////        ResponseEntity<String> responseEntity = restTemplate.getForEntity(externalServerUrl, String.class);
////        return ResponseEntity.ok().header(JwtProvider.HEADER, jwt).body(responseEntity);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(JwtProvider.HEADER, jwt);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                externalServerUrl, HttpMethod.GET, entity, String.class);
//
//        return ResponseEntity.ok()
//                .header(JwtProvider.HEADER, jwt)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response.getBody());
//
//    }
    @GetMapping("/user/main")
    public ResponseEntity<?> getMain() {
        String externalServerUrl = "http://localhost:8070/main"; // 외부 서버의 URL
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                externalServerUrl, HttpMethod.GET, entity, String.class);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.getBody());

    }

    @GetMapping("/user/detail")
    public ResponseEntity<?> getDetails() {
        String externalServerUrl = "http://localhost:8070/main"; // 외부 서버의 URL
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                externalServerUrl, HttpMethod.GET, entity, String.class);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.getBody());

    }
}
