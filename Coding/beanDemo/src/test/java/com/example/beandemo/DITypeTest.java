package com.example.beandemo;

import com.example.beandemo.diType.ConstructorInjection;
import com.example.beandemo.diType.FieldInjection;
import com.example.beandemo.diType.SetterInjection;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class DITypeTest {

    @Test
    void constructorInjectionTest() {
        ApplicationContext context = SpringApplication.run(BeanDemoApplication.class);
        context.getBean(ConstructorInjection.class).doSomething();
    }

    @Test
    void setterInjectionTest() {
        ApplicationContext context = SpringApplication.run(BeanDemoApplication.class);
        context.getBean(SetterInjection.class).doSomething();
    }

    @Test
    void fieldInjectionTest() {
        ApplicationContext context = SpringApplication.run(BeanDemoApplication.class);
        context.getBean(FieldInjection.class).doSomething();
    }
}
