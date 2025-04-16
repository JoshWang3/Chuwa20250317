package com.example.q15.controller;

import com.example.q15.printer.UserPrinter;
import com.example.q15.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserController {
    private final UserService userService;
    private final UserPrinter userPrinter;

    @Autowired // constructor injection
    public UserController(UserService userService, UserPrinter userPrinter) {
        this.userService = userService;
        this.userPrinter = userPrinter;
    }

    public void printUser() {
        System.out.println(userService.getUserName());
    }
}
