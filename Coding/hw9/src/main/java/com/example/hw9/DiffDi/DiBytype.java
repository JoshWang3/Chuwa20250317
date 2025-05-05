package com.example.hw9.DiffDi;

import com.example.hw9.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiBytype {
  private final UserService userService;

  @Autowired
  public DiBytype(UserService userService) {
    this.userService = userService;
  }

  public void showSomething() {
    System.out.println();
    System.out.println("--------------------------------------------------");
    System.out.println("------------ DI by type with @Primary ------------");
    System.out.println("--------------------------------------------------");
    userService.showSomething();
  }
}
