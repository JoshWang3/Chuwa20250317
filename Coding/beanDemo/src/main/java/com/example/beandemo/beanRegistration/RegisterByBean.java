package com.example.beandemo.beanRegistration;

import com.example.beandemo.service.UserService;
import com.example.beandemo.service.impl.MaleUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegisterByBean {

    @Bean
    public UserService customUserService() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("--------------- Register By @Bean ----------------");
        System.out.println("--------------------------------------------------");
        return new MaleUserServiceImpl();
    }
}
