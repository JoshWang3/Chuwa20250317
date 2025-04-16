package com.example.beandemo;

import com.example.beandemo.beanScope.PrototypeBean;
import com.example.beandemo.beanScope.SingletonBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class BeanScopeTest {

    @Test
    void singletonBeanTest() {
        ApplicationContext context = SpringApplication.run(BeanDemoApplication.class);
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("----------------- Singleton Bean -----------------");
        System.out.println("--------------------------------------------------");
        SingletonBean sb1 = context.getBean(SingletonBean.class);
        SingletonBean sb2 = context.getBean(SingletonBean.class);
        System.out.println("---------- sb1 == sb2: " + (sb1 == sb2));
    }

    @Test
    void prototypeBeanTest() {
        ApplicationContext context = SpringApplication.run(BeanDemoApplication.class);
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("----------------- Singleton Bean -----------------");
        System.out.println("--------------------------------------------------");
        PrototypeBean sb1 = context.getBean(PrototypeBean.class);
        PrototypeBean sb2 = context.getBean(PrototypeBean.class);
        System.out.println("---------- sb1 == sb2: " + (sb1 == sb2));
    }
}
