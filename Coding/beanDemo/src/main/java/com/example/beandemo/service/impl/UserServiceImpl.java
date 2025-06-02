package com.example.beandemo.service.impl;

import com.example.beandemo.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void doSomething() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("------------ UserServiceImpl running -------------");
        System.out.println("--------------------------------------------------");
    }
}
