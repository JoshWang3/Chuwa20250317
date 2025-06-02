package com.example.beandemo.diBy;

import com.example.beandemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DIByName {
    private final UserService userService;

    @Autowired
    public DIByName(@Qualifier("femaleUserServiceImpl") UserService userService) {
        this.userService = userService;
    }


    public void doSomething() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("----------- DI by name with @Qualifier ------------");
        System.out.println("--------------------------------------------------");
        userService.doSomething();
    }
}
