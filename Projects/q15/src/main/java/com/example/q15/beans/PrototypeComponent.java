package com.example.q15.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeComponent {
    public String getScope() {
        return "Prototype";
    }
}