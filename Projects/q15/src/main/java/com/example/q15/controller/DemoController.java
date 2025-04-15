package com.example.q15.controller;

import com.example.q15.service.ServiceA;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private final ServiceA serviceA;

    public DemoController(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    @GetMapping("/demo")
    public String showInfo() {
        return serviceA.getInfo();
    }
}
