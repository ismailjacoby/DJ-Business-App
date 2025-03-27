package com.ismailjacoby.djbusinessappapi.service.implementation;

import com.ismailjacoby.djbusinessappapi.config.security.JWTProvider;
import com.ismailjacoby.djbusinessappapi.dto.AuthDTO;
import com.ismailjacoby.djbusinessappapi.exception.DuplicateException;
import com.ismailjacoby.djbusinessappapi.exception.InvalidCredentialsException;
import com.ismailjacoby.djbusinessappapi.exception.NotFoundException;
import com.ismailjacoby.djbusinessappapi.form.LoginForm;
import com.ismailjacoby.djbusinessappapi.form.SignupForm;
import com.ismailjacoby.djbusinessappapi.model.entity.User;
import com.ismailjacoby.djbusinessappapi.repository.UserRepository;
import com.ismailjacoby.djbusinessappapi.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
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

    @Override
    public AuthDTO login(LoginForm form) {
        if (form == null) {
            throw new IllegalArgumentException("Form cannot be null.");
        }

        String username = form.username().toLowerCase().trim();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, form.password())
            );
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Invalid username or password");
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));

        String token = jwtProvider.generateToken(user.getUsername(), user.getRole());

        return AuthDTO.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
