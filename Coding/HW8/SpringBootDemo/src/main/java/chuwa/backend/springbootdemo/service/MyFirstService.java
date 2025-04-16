package chuwa.backend.springbootdemo.service;

import chuwa.backend.springbootdemo.service.impl.ServiceInterface;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Primary
@Scope("prototype")
public class MyFirstService implements ServiceInterface {
}
