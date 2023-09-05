package com.example.becarefulbackendapi.controller;

import com.example.becarefulbackendapi.config.auth.OauthService;
import com.example.becarefulbackendapi.config.auth.PrincipalDetails;
import com.example.becarefulbackendapi.dto.KakaoProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {
    private final OauthService oauthService;

    @GetMapping("/test/oauth/login")
    public @ResponseBody String loginTest(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2){
        System.out.println("/test/oauth/login ============");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication: "+ oAuth2User.getAttributes());
        System.out.println("oauth2User : "+oAuth2.getAttributes());

        return "세션 정보 확인";
    }
    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    @GetMapping("/loginForm")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

//    @GetMapping("/user")
//    public @ResponseBody String joinProc() {
//        return "유저화면";
//    }
//
////    @GetMapping("/auth/kakao/callback")
////    public  String kakaoCalllback(String code) throws IOException {
//////        //TODO : POST방식으로 key=value 데이터를 요청(카카오로)
//////        //httpHeader 오브젝트 생성
//////        RestTemplate rt = new RestTemplate();
//////        HttpHeaders headers = new HttpHeaders();
//////        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
//////
//////        //전송할 http 바디의 데이터형태를 전달
//////        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
//////        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
//////        params.add("grant_type","authorization_code");
//////        params.add("client_id","7173ac3049183122f6704ddf9b43425d");
//////        params.add("redirect_uri","http://localhost:8080/auth/kakao/callback");
//////        params.add("code",code);
//////
//////        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
//////            new HttpEntity<>(params,headers);
//////
//////        //Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
//////        ResponseEntity<String> response = rt.exchange(
//////                "https://kauth.kakao.com/oauth/token",
//////                HttpMethod.POST,
//////                kakaoTokenRequest,
//////                String.class
//////        );
////
////        String accessToken = oauthService.getKaKaoAccessToken(code);
////        KakaoProfile response = oauthService.getKakaoUserInfo(accessToken);
////
////        return "agree";
////        return "카카오 유저 정보 :"+response;
//
//    }

    @GetMapping("user/agree")
    public String agree(){
        return "agree";
    }
}
