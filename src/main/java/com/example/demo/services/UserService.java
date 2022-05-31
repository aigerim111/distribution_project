package com.example.demo.services;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Organization;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.OrganizationRepo;
import com.example.demo.repositories.RoleRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final OrganizationRepo orgRepo;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepo userRepo, OrganizationRepo orgRepo, RoleRepo roleRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.orgRepo = orgRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    public User registerUser(LoginDTO loginDTO, Long orgId){
        User user = new User();
        Organization organization = orgRepo.findByOrgId(orgId).orElseThrow();
        user.setUsername(loginDTO.getUsername());
        user.setEmail(loginDTO.getEmail());
        user.setPassword(encoder.encode(loginDTO.getPassword()));

        Role role = roleRepo.findByName("user").orElseThrow(); //demo
        user.setOrg(organization);
        user.getRoles().add(role);

        return userRepo.save(user);
    }

    public User addUserData(UserDTO userDTO, Long userId){
        User user = userRepo.findByUserId(userId).orElseThrow();
        user.setNameKz(userDTO.getNameKZ());
        user.setNameRu(userDTO.getNameRu());
        user.setPhone(userDTO.getPhone());

        return userRepo.save(user);
    }

    public List<User> allUsers() {
        return userRepo.findAll();
    }

}
