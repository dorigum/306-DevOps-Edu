package web.mvc.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import web.mvc.jwt.JWTFilter;
import web.mvc.jwt.JWTUtil;
import web.mvc.jwt.LoginFilter;

@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig {
    // AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;

    private final JWTUtil jwtUtil;

    // AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        log.info("bCryptPasswordEncoder() 실행");
        return new BCryptPasswordEncoder(); // passwordEncoder의 구현체 중 하나
    }

    // 인증과 인가에 관련된 정책을 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("SecurityFilterChain filterChain(HttpSecurity http) 실행");

        // csrf disable
        http.csrf((auth) -> auth.disable()); // csrf 공격을 방어하기 위한 토큰을 주고 받는 부분을 비활성화!

        // Form 로그인 방식 disable -> React, JWT 인증 방식으로 변경 예정
        // disable을 설정하면 시큐리티의 UsernamePasswordAuthenticationFilter 비활성화
        http.formLogin((auth) -> auth.disable());

        // http basic 인증 방식 disable
        http.httpBasic((auth) -> auth.disable());

        // JWT는 서버 세션을 사용하지 않음
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 경로별 인가 작업
        http.authorizeHttpRequests((auth) ->
                auth.requestMatchers("/index", "/members", "/members/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/boards").permitAll()
                        .requestMatchers(HttpMethod.GET, "/boards/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/boards").authenticated()
                        .requestMatchers(HttpMethod.POST, "/boards/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/boards/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/boards/**").authenticated()

                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());

        // 로그인 필터 등록
        // 필터 추가 LoginFilter()는 인자를 받음
        // (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함)
        // addFilterAt은 UsernamePasswordAuthenticationFilter의 자리에 LoginFilter가 실행되도록 설정하는 것
        http.addFilterAt(new LoginFilter(this.authenticationManager(authenticationConfiguration), jwtUtil),
                UsernamePasswordAuthenticationFilter.class);

        // JWTFilter 등록
        http.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        SecurityFilterChain chain = http.build();
        System.out.println("-------------------------------");
        chain.getFilters().forEach(System.out::println);
        System.out.println("-------------------------------");

        return chain;
    }
}
