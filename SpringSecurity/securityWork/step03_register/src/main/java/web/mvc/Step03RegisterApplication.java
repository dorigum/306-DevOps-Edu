package web.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Step03RegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(Step03RegisterApplication.class, args);
    }

}