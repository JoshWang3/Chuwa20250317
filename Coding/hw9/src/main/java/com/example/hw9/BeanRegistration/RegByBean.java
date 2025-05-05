package com.example.hw9.BeanRegistration;

import com.example.hw9.Service.Impl.UserService1Impl;
import com.example.hw9.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegByBean {

  @Bean
  public UserService custUserService() {
    System.out.println();
    System.out.println("--------------------------------------------------");
    System.out.println("--------------- Register By @Bean ----------------");
    System.out.println("--------------------------------------------------");
    return new UserService1Impl();
  }

}
