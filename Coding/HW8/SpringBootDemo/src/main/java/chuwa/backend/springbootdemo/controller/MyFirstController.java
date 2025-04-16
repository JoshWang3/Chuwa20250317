package chuwa.backend.springbootdemo.controller;

import chuwa.backend.springbootdemo.component.MyComponent;
import chuwa.backend.springbootdemo.service.impl.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MyFirstController {

    private ServiceInterface serviceInterface;
    private MyComponent myComponent;

    @Autowired
    public MyFirstController(ServiceInterface serviceInterface, MyComponent myComponent) {
        this.serviceInterface = serviceInterface;
        this.myComponent = myComponent;
        System.out.println("---------------------------------------");
        System.out.println("MyFirstController: " + serviceInterface.getClass().getName());
    }
}
