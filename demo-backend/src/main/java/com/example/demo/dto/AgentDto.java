package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter

public class AgentDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<String> roles;

    public AgentDto() {
    }

    public AgentDto(Long id, String name, String username, String email, String password, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
