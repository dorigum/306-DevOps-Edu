package web.mvc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        log.info("bCryptPasswordEncoder() 실행");
        return new BCryptPasswordEncoder();
    }

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

        // 경로별 인가 작업
        http.authorizeHttpRequests((auth) ->
                auth.requestMatchers("/index", "/members", "/members/**", "/boards").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());

        return http.build();
    }
}
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/user/login")
//                .hasRole("USER")
//                .antMatchers("/member/**")
//                .authenticated()
//                .antMatchers("/admin/**")
//                .hasRole("ADMIN")
//                .and()
//                // .csrf().disable() // <security:csrf disabled="true"/>
//                .formLogin()
//                .loginPage("/user/loginForm")
//                .loginProcessingUrl("/loginCheck")
//                .usernameParameter("id")
//                .passwordParameter("pwd")
//                .defaultSuccessUrl("/")
//                .failureForwardUrl("/user/loginForm?err")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .and();