package com.example.beandemo;

import com.example.beandemo.beanRegistration.RegisterByComponent;
import com.example.beandemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class BeanRegistrationTest {

    @Test
    void registerByComponentTest() {
        ApplicationContext context = SpringApplication.run(BeanDemoApplication.class);
        context.getBean(RegisterByComponent.class).doSomething();
    }
    @Test
    void registerByBeanTest() {
        ApplicationContext context = SpringApplication.run(BeanDemoApplication.class);
        context.getBean("customUserService", UserService.class).doSomething();
    }
}
