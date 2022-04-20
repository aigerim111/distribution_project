package com.example.demo.services;

import com.example.demo.entities.DictUserType;
import com.example.demo.entities.Login;
import com.example.demo.entities.Organization;
import com.example.demo.entities.User;
import com.example.demo.repositories.DictUserTypeRepo;
import com.example.demo.repositories.LoginRepo;
import com.example.demo.repositories.OrganizationRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepo loginRepo;
    private final UserRepo userRepo;
    private final OrganizationRepo organizationRepo;
    private final DictUserTypeRepo dictUserTypeRepo;

    public LoginService(LoginRepo loginRepo, UserRepo userRepo, OrganizationRepo organizationRepo, DictUserTypeRepo dictUserTypeRepo) {
        this.loginRepo = loginRepo;
        this.userRepo = userRepo;
        this.organizationRepo = organizationRepo;
        this.dictUserTypeRepo = dictUserTypeRepo;
    }

    public Login createLogin(Login login, Long orgId, Long dictUserTypeId){
        User user = new User();
        DictUserType dictUserType = dictUserTypeRepo.findById(dictUserTypeId).orElseThrow();
        Organization organization = organizationRepo.findByOrgId(orgId).orElseThrow();
        user.setDictUserType(dictUserType);
        user.setOrg(organization);
        login.setUser(user);

        return loginRepo.save(login);
    }

}
