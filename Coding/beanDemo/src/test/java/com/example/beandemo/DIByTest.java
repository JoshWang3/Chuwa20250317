package com.example.beandemo;

import com.example.beandemo.diBy.DIByName;
import com.example.beandemo.diBy.DIByType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class DIByTest {

    @Test
    void diByTypeTest() {
        ApplicationContext context = SpringApplication.run(BeanDemoApplication.class);
        context.getBean(DIByType.class).doSomething();
    }

    @Test
    void diByNameTest() {
        ApplicationContext context = SpringApplication.run(BeanDemoApplication.class);
        context.getBean(DIByName.class).doSomething();
    }
}
