package com.example.hw9.Service.Impl;

import com.example.hw9.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserService1Impl implements UserService {

  @Override
  public void showSomething() {
    System.out.println();
    System.out.println("--------------------------------------------------");
    System.out.println("---------- UserService1Impl running -----------");
    System.out.println("--------------------------------------------------");
    System.out.println();
  }
}
