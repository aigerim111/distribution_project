package com.example.demo.controller;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/{orgId}")
    public ResponseEntity<User> registerUser(@RequestParam("email") String email,
                                             @RequestParam("roleName") String roleName,
                                             @PathVariable("orgId") String orgId){
        return new ResponseEntity<>(userService.createUser(email, roleName,Long.parseLong(orgId)), HttpStatus.OK);
    }
}
