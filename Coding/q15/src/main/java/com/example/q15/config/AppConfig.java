package com.example.q15.config;

import com.example.q15.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean("customService")
    public UserService customUserService() {
        return () -> "Custom User from @Bean";
    }

}
