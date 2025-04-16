package chuwa.backend.springbootdemo.controller;

import chuwa.backend.springbootdemo.service.impl.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class MyThirdController {

    @Autowired
    private ServiceInterface serviceInterface;

}
