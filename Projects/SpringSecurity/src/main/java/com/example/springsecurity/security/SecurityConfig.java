package com.example.springsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 开发时可以关闭 CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 所有请求不需要认证
                )
                .formLogin().disable(); // 禁用默认登录表单

        return http.build();
    }
}