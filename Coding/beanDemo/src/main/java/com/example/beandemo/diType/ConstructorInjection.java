package com.example.beandemo.diType;

import com.example.beandemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConstructorInjection {
    private final UserService userService;

    @Autowired
    public ConstructorInjection(UserService userService) {
        this.userService = userService;
    }

    public void doSomething() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("------------- Constructor Injection --------------");
        System.out.println("--------------------------------------------------");
        userService.doSomething();
    }
}
