package com.example.q15;

import com.example.q15.controller.UserController;
import com.example.q15.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Q15Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Q15Application.class, args);

        // Constructor injection demo: UserController will get AdminService (due to @Primary)
        System.out.println("---- Constructor Injection Demo ----");
        UserController controller = context.getBean(UserController.class);
        controller.printUser();

        // Prototype scope demo: GuestService is defined as prototype so each retrieval is a new instance.
        System.out.println("---- Prototype Scope Demo ----");
        UserService guest1 = context.getBean("guestService", UserService.class);
        UserService guest2 = context.getBean("guestService", UserService.class);
        System.out.println("Are guestService instances different? " + (guest1 != guest2));

        // @Bean registration demo: customService was registered manually in AppConfig.
        System.out.println("---- @Bean Registration Demo ----");
        UserService custom = context.getBean("customService", UserService.class);
        System.out.println("Custom bean says: " + custom.getUserName());
    }

}
