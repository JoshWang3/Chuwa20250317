package com.example.hw9.DiType;

import com.example.hw9.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetterInjection {
  private UserService userService;
  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
  public void doSomething() {
    System.out.println();
    System.out.println("--------------------------------------------------");
    System.out.println("---------------- Setter Injection ----------------");
    System.out.println("--------------------------------------------------");
    userService.showSomething();
  }
}
