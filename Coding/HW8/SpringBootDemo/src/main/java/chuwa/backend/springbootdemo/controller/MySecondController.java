package chuwa.backend.springbootdemo.controller;

import chuwa.backend.springbootdemo.service.impl.ServiceInterface;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySecondController {

    private ServiceInterface serviceInterface;

    @Autowired
    @Qualifier("mySecondService")
    public void setServiceInterface(ServiceInterface serviceInterface) {
        this.serviceInterface = serviceInterface;
        System.out.println("---------------------------------------");
        System.out.println("MySecondController: " + serviceInterface.getClass().getName());
    }


}
