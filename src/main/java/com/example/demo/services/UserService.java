package com.example.demo.services;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //create user



    private final UserRepo userRepo;


    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
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
