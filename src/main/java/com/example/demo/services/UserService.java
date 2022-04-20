package com.example.demo.services;

import com.example.demo.entities.Login;
import com.example.demo.entities.Organization;
import com.example.demo.entities.User;
import com.example.demo.repositories.LoginRepo;
import com.example.demo.repositories.OrganizationRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final LoginRepo loginRepo;
    private final OrganizationRepo organizationRepo;


    public UserService(UserRepo userRepo, LoginRepo loginRepo, OrganizationRepo organizationRepo) {
        this.userRepo = userRepo;
        this.loginRepo = loginRepo;
        this.organizationRepo = organizationRepo;
    }

}
