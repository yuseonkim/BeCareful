package com.example.becarefulbackendapi.config;

//import com.example.becarefulbackendapi.config.auth.AuthenticationSuccessHandler;
import com.example.becarefulbackendapi.config.Jwt.JwtAuthenticationFilter;
import com.example.becarefulbackendapi.config.errors.exception.Exception401;
import com.example.becarefulbackendapi.config.errors.exception.Exception403;
import com.example.becarefulbackendapi.config.oauth.PrincipalOauth2UserService;
import com.example.becarefulbackendapi.config.utils.FilterResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //secured 어노테이션 활성화
//스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
public class SecurityConfig {

    //해당 메소드의 리턴오브젝트를 IOC에 등록


    private final PrincipalOauth2UserService principalOauth2UserService;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public BCryptPasswordEncoder encodedPwd() {
        return new BCryptPasswordEncoder();
    }

    public class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            builder.addFilter(new JwtAuthenticationFilter(authenticationManager));
            super.configure(builder);
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            FilterResponseUtils.unAuthorized(response, new Exception401("인증되지 않았습니다"));
        });

        // 9. 권한 실패 처리
        http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            FilterResponseUtils.forbidden(response, new Exception403("권한이 없습니다"));
        });
        http.apply(new CustomSecurityFilterManager());

        http.cors();


        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                .loginPage("/loginForm")
                .defaultSuccessUrl("https://localhost:3000/")
                .successHandler(authenticationSuccessHandler)
                .userInfoEndpoint()
                .userService(principalOauth2UserService);

        return http.build();
    }


    // 로그인 완료 시 메인페이지로 이동
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*"); // GET, POST, PUT, DELETE (Javascript 요청 허용)
        configuration.addAllowedOriginPattern("*"); // 모든 IP 주소 허용 (프론트 앤드 IP만 허용 react)
        configuration.setAllowCredentials(true); // 클라이언트에서 쿠키 요청 허용
        configuration.addExposedHeader("Authorization"); // 옛날에는 디폴트 였다. 지금은 아닙니다.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}


