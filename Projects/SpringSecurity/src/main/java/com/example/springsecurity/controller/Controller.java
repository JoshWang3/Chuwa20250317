package com.example.springsecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/secure")
    public String secureEndpoint() {
        return "This is a secure endpoint over HTTPS!";
    }
}
