package com.example.beandemo.diBy;

import com.example.beandemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DIByType {
    private final UserService userService;

    @Autowired
    public DIByType(UserService userService) {
        this.userService = userService;
    }

    public void doSomething() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("------------ DI by type with @Primary ------------");
        System.out.println("--------------------------------------------------");
        userService.doSomething();
    }
}
