package com.ismailjacoby.djbusinessappapi.service;

import com.ismailjacoby.djbusinessappapi.dto.AuthDTO;
import com.ismailjacoby.djbusinessappapi.form.LoginForm;
import com.ismailjacoby.djbusinessappapi.form.SignupForm;

public interface UserService {
    void signup(SignupForm form);
    AuthDTO login(LoginForm form);
}
