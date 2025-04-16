package com.example.q15.printer;

import com.example.q15.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserPrinter {
    private UserService userService;

    @Autowired // setter injection
    @Qualifier("guestService") // qualifier
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void printService(String name) {
        System.out.println("Hello, " + name);
        System.out.println("From printer with injected user: " + userService.getUserName());
    }
}
