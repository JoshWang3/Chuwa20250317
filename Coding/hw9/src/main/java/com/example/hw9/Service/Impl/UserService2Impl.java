package com.example.hw9.Service.Impl;

import com.example.hw9.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserService2Impl implements UserService {

  @Override
  public void showSomething() {
      System.out.println();
      System.out.println("--------------------------------------------------");
      System.out.println("---------- UserService2Impl running -----------");
      System.out.println("--------------------------------------------------");
      System.out.println();
  }
}
