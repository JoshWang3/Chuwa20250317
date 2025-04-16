package com.example.q15.service.impl;

import com.example.q15.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component("guestService") // injection by name
@Scope("prototype") // prototype bean scope
public class GuestService implements UserService {
    @Override
    public String getUserName() {
        return "Guest User";
    }
}
