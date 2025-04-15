package com.example.q15.beans;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SingletonComponent {
    public String getScope() {
        return "Singleton";
    }
}
