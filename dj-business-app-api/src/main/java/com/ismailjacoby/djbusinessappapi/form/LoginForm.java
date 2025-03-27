package com.ismailjacoby.djbusinessappapi.form;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginForm(
        @NotNull(message = "Username cannot be null.")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long.")
        String username,

        @NotNull(message = "Password cannot be null.")
        @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters long.")
        String password
) {
}
