package com.example.becarefulbackendapi.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    //후처리 되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest : "+userRequest);
        System.out.println("userRequest : "+userRequest.getClientRegistration());
        System.out.println("userRequest : "+userRequest.getAccessToken());
        System.out.println("userRequest : "+super.loadUser(userRequest));

        OAuth2User oAuth2User = super.loadUser(userRequest);
        return super.loadUser(userRequest);
    }
}
