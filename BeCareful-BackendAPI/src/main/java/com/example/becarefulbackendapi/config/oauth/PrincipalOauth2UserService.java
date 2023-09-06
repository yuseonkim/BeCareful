package com.example.becarefulbackendapi.config.oauth;

import com.example.becarefulbackendapi.config.auth.PrincipalDetails;
import com.example.becarefulbackendapi.domain.User;
import com.example.becarefulbackendapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;
    //후처리 되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest : "+userRequest);
        System.out.println("userRequest : "+userRequest.getClientRegistration());
        System.out.println("userRequest : "+userRequest.getAccessToken());
        System.out.println("userRequest : "+super.loadUser(userRequest));

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("getAttributes : "+oAuth2User.getAttributes() );

        String provider = userRequest.getClientRegistration().getRegistrationId(); //kakao
        Long providerId = oAuth2User.getAttribute("id");
        Map<String,String> map = oAuth2User.getAttribute("properties");
        String nickname = map.get("nickname");
        String username = provider+"_"+providerId;
        String password = bCryptPasswordEncoder.encode("비케어풀");
        String role = "ROLE_USER";

        User userEntity =  userRepository.findByUsername(username);

        if(userEntity == null ) {
            userEntity = new User(username, password, role,nickname, provider, providerId);
            userRepository.save(userEntity);
        }

        return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
    }
}
