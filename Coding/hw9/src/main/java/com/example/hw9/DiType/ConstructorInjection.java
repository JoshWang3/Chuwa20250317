package com.example.hw9.DiType;

import com.example.hw9.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConstructorInjection {
  private final UserService userService;

  @Autowired
  public ConstructorInjection(UserService userService) {
    this.userService = userService;
  }

  public void doSomething() {
    System.out.println();
    System.out.println("--------------------------------------------------");
    System.out.println("------------- Constructor Injection --------------");
    System.out.println("--------------------------------------------------");
    userService.showSomething();
  }
}
