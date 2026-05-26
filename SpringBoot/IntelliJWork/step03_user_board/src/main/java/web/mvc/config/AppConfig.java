package web.mvc.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class AppConfig {
    @Bean
    public ModelMapper getModelMapper() {

        return new ModelMapper();
    }
}
