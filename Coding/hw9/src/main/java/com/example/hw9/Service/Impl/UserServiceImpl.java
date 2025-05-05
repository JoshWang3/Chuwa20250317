package com.example.hw9.Service.Impl;

import com.example.hw9.Service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class UserServiceImpl implements UserService {

  @Override
  public void showSomething() {
    System.out.println();
    System.out.println("--------------------------------------------------");
    System.out.println("------------ UserServiceImpl running -------------");
    System.out.println("--------------------------------------------------");
    System.out.println();
  }

}
