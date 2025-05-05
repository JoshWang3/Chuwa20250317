package com.example.hw9.BeanRegistration;

import com.example.hw9.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegByComponent {
  private final UserService userService;

  @Autowired
  public RegByComponent(UserService userService) {
    this.userService = userService;
  }

  public void showSomething() {
    System.out.println();
    System.out.println("--------------------------------------------------");
    System.out.println("------------- Register By @Component -------------");
    System.out.println("--------------------------------------------------");
    userService.showSomething();
  }
}
