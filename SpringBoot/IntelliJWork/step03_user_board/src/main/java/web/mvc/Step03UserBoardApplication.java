package web.mvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import web.mvc.domain.User;
import web.mvc.repository.UserRepository;

@SpringBootApplication
public class Step03UserBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(Step03UserBoardApplication.class, args);
    }

    @Bean
    CommandLineRunner initUser(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("doyeon", "1111", "도연"));
        };
    }
}