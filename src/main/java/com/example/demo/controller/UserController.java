package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user/{userid}")
    public ResponseEntity<User> addUserData(@RequestBody UserDTO userDTO,
                                            @PathVariable("userid") String userId){
        return new ResponseEntity<>(
                userService.addUserData(userDTO, Long.parseLong(userId)), HttpStatus.OK);
    }



    @GetMapping("/userlist")
    public ResponseEntity<List<User>> allUsers(){
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activateAcc(@PathVariable("code") String token) {

        try {
            userService.activateUser(token);
            return new ResponseEntity<>("Your account activated successfully", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

}
