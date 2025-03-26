package com.ismailjacoby.djbusinessappapi.service.implementation;

import com.ismailjacoby.djbusinessappapi.exception.DuplicateException;
import com.ismailjacoby.djbusinessappapi.form.SignupForm;
import com.ismailjacoby.djbusinessappapi.model.entity.User;
import com.ismailjacoby.djbusinessappapi.repository.UserRepository;
import com.ismailjacoby.djbusinessappapi.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signup(SignupForm form) {
        if(form == null) {
            throw new IllegalArgumentException("Form cannot be null.");
        }

        String username = form.username().toLowerCase().trim();
        String email = form.email().toLowerCase().trim();

        if(userRepository.existsByEmail(email)){
            throw new DuplicateException("Email: '" + email + "' is already registered.");
        }

        if(userRepository.existsByUsername(username)){
            throw new DuplicateException("Username: '" + username + "' is already taken.");
        }

        User user = new User();
        user.setFirstName(form.firstName());
        user.setLastName(form.lastName());
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(form.password()));
        userRepository.save(user);
    }
}
