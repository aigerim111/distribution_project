package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/{orgId}")
    public ResponseEntity<User> registerUser(@RequestParam("email") String email,
                                             @RequestParam("userRole") String userRole,
                                             @PathVariable("orgId") String orgId){
        return new ResponseEntity<>(userService.registerUser(email, userRole, Long.parseLong(orgId)), HttpStatus.OK);
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
