package com.ismailjacoby.djbusinessappapi.controller;

import com.ismailjacoby.djbusinessappapi.form.SignupForm;
import com.ismailjacoby.djbusinessappapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody @Valid SignupForm form){
        userService.signup(form);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
