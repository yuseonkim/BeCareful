package com.example.becarefulbackendapi.config.Jwt;

import com.example.becarefulbackendapi.domain.User;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;


import java.util.Date;

@Component
public class JwtProvider {
    public static final Long EXP = 1000L * 60 * 60 * 48; // 48시간 - 테스트 하기 편함.
    public static final String TOKEN_PREFIX = "Bearer "; // 스페이스 필요함
    public static final String HEADER = "Authorization";
    public static final String SECRET = "MySecretKey";

    public static String create(String username, String nickname) {
        String jwt = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + EXP))
                .withClaim("username", username)
                .withClaim("nickname", nickname)
                .sign(Algorithm.HMAC512(SECRET));
        return TOKEN_PREFIX + jwt;
    }

    public static DecodedJWT verify(String jwt) throws SignatureVerificationException, TokenExpiredException {
        jwt = jwt.replace(JwtProvider.TOKEN_PREFIX, "");
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET))
                .build().verify(jwt);
        return decodedJWT;
    }
}
