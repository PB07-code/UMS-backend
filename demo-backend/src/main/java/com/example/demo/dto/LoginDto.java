package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LoginDto {

    @NonNull
    private String usernameOrEmail;
    private String password;
}