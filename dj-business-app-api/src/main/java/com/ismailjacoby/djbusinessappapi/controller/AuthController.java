package com.ismailjacoby.djbusinessappapi.controller;

import com.ismailjacoby.djbusinessappapi.form.SignupForm;
import com.ismailjacoby.djbusinessappapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupForm form){
        userService.signup(form);
        return ResponseEntity.status(201).body("User registered successfully.");
    }
}
