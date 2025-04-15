package com.example.q15.service;

import com.example.q15.beans.PlainBean;
import com.example.q15.beans.PrototypeComponent;
import com.example.q15.beans.SingletonComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ServiceA {

    private final SingletonComponent singletonComponent; // Constructor injection

    @Autowired
    private PrototypeComponent prototypeComponent; // Field injection

    private PlainBean plainBean;

    @Resource(name = "plainBean")
    private PlainBean resourceInjectedBean; // Resource injection

    @Autowired
    public ServiceA(SingletonComponent singletonComponent) {
        this.singletonComponent = singletonComponent;
    }

    @Autowired
    public void setPlainBean(@Qualifier("plainBean") PlainBean plainBean) {
        this.plainBean = plainBean;
    }

    public String getInfo() {
        return """
               Singleton Scope: %s
               Prototype Scope: %s
               Setter Injected Bean: %s
               Resource Injected Bean: %s
               """.formatted(
                singletonComponent.getScope(),
                prototypeComponent.getScope(),
                plainBean.getName(),
                resourceInjectedBean.getName()
        );
    }
}

