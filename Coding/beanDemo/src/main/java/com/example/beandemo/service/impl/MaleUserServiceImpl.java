package com.example.beandemo.service.impl;

import com.example.beandemo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class MaleUserServiceImpl implements UserService {
    @Override
    public void doSomething() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("---------- MaleUserServiceImpl running -----------");
        System.out.println("--------------------------------------------------");
    }
}
