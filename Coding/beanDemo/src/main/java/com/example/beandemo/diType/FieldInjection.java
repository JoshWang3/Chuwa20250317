package com.example.beandemo.diType;

import com.example.beandemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FieldInjection {
    @Autowired
    private UserService userService;

    public void doSomething() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("---------------- Field Injection -----------------");
        System.out.println("--------------------------------------------------");
        userService.doSomething();
    }
}
