package com.example.q15.config;

import com.example.q15.beans.PlainBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "plainBean")
    public PlainBean plainBean() {
        return new PlainBean("Plain Bean from @Bean");
    }
}
