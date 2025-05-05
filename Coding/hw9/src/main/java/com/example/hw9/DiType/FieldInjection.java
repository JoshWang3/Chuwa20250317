package com.example.hw9.DiType;

import com.example.hw9.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FieldInjection {
  @Autowired
  private UserService userService;

  public void doSomething() {
    System.out.println();
    System.out.println("--------------------------------------------------");
    System.out.println("------------- Field Injection --------------");
    System.out.println("--------------------------------------------------");
    userService.showSomething();
  }
}
