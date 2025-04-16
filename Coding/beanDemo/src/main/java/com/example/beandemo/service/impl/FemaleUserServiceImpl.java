package com.example.beandemo.service.impl;

import com.example.beandemo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class FemaleUserServiceImpl implements UserService {
    @Override
    public void doSomething() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("--------- FemaleUserServiceImpl running ----------");
        System.out.println("--------------------------------------------------");
    }
}
