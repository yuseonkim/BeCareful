package com.example.becarefulbackendapi.service;

import com.example.becarefulbackendapi.config.auth.PrincipalDetails;
import com.example.becarefulbackendapi.config.errors.exception.Exception500;
import com.example.becarefulbackendapi.domain.User;
import com.example.becarefulbackendapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    public void LocationSubmitAgree(String username, String check) {
//        User user = userRepository.findByUsername(username);
//        System.out.println(username);
//        System.out.println(user);
//        user.updateAgree(true);
//        userRepository.saveAndFlush(user);
//
//    }

}
