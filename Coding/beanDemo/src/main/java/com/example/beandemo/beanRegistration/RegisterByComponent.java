package com.example.beandemo.beanRegistration;

import com.example.beandemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterByComponent {
    private final UserService userService;

    @Autowired
    public RegisterByComponent(UserService userService) {
        this.userService = userService;
    }

    public void doSomething() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("------------- Register By @Component -------------");
        System.out.println("--------------------------------------------------");
        userService.doSomething();
    }
}
