package fr.isep.comService.application.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class SpringConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
