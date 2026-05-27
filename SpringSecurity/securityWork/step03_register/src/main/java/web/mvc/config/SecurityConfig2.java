package web.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig2 {
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/login")
                .hasRole("USER")
                .antMatchers("/member/**")
                .authenticated()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .and()
                // .csrf().disable() // <security:csrf disabled="true"/>
                .formLogin()
                .loginPage("/user/loginForm")
                .loginProcessingUrl("/loginCheck")
                .usernameParameter("id")
                .passwordParameter("pwd")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/user/loginForm?err")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and();

        return http.build();
    }
}