package com.example.q15.service.impl;

import com.example.q15.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Primary // injection by type
@Scope("singleton") // singleton bean scope
public class AdminService implements UserService {
    @Override
    public String getUserName() {
        return "Admin User";
    }
}
