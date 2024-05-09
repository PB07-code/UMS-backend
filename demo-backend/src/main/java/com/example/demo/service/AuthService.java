package com.example.demo.service;

import com.example.demo.dto.*;

import java.util.List;

public interface AuthService {

    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);

    List<AgentDto> getAllAgents();

    //Change Role
    public AgentDto updateAgentRole(Long agentId, String role);
}
