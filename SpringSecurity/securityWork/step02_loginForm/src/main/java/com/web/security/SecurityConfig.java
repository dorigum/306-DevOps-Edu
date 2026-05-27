package com.web.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 환경 설정을 돕는 클래스
@EnableWebSecurity // Spring Security를 활성화하는 어노테이션
@Slf4j
public class SecurityConfig {

    /*
     * SecurityFilterChain은 security 정책
     * : HttpSecurity는 각 요청에 해당하는 정책들을 어떻게 할 것인지 결정
     *   ex) 어떤 정책은 무엇을 해야하고, 정책 수행 여부를 어떻게 할 것인지 등의 옵션을 설정할 수 있다.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        // http.formLogin(Customizer.withDefaults()); // 기본 화면

        // 사용자 정의 로그인폼 설정
        http.formLogin(form ->
                form.loginPage("/loginPage")
                        .loginProcessingUrl("/loginProc")
//                        .defaultSuccessUrl("/", false)
//                        .failureUrl("/failed")
                        .usernameParameter("userId")
                        .passwordParameter("userPass")
                        .successHandler((request, response, authentication) -> {
                            System.out.println("authentication = " + authentication);
                            response.sendRedirect("/home");
                        })
                        .failureHandler((request, response, exception) -> {
                            System.out.println("exception = " + exception);
                            response.sendRedirect("/loginPage");
                        })
                        .permitAll());

        http.csrf(csrf -> csrf.disable());

        SecurityFilterChain chain = http.build();
        System.out.println("---------------------------------------");
        chain.getFilters().forEach((filter) -> {
            System.out.println(filter);
        });
        System.out.println("---------------------------------------");

        return chain;
    }

    // 여러 명의 계정을 추가 - inMemory
    // SecurityFilterChain: 인증 + 인가 정책 설정
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("koo").password("{noop}1111").roles("USER").build());
        manager.createUser(User.withUsername("doyeon").password("{noop}2222").roles("USER").build());
        manager.createUser(User.withUsername("kim").password("{noop}3333").roles("USER", "ADMIN").build());

        return manager;
    }
}