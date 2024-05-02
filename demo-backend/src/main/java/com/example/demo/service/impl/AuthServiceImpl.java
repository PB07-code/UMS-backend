package com.example.demo.service.impl;

import com.example.demo.dto.AgentDto;
import com.example.demo.dto.JwtAuthResponse;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.Agent;
import com.example.demo.entity.Role;
import com.example.demo.exception.TodoAPIException;
import com.example.demo.mapper.AgentMapper;
import com.example.demo.repository.AgentRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AgentRepository agentRepository;
    private RoleRepository roleRepository;
   private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;


    @Override
    public String register(RegisterDto registerDto) {

        // check username is already exists in database
        if(agentRepository.existsByUsername(registerDto.getUsername())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Agent already exists!");
        }

        // check email is already exists in database
        if(agentRepository.existsByEmail(registerDto.getEmail())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        Agent agent = new Agent();
        agent.setName(registerDto.getName());
        agent.setUsername(registerDto.getUsername());
        agent.setEmail(registerDto.getEmail());
        agent.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role agentRole = roleRepository.findByName("ROLE_USER");
        roles.add(agentRole);

        agent.setRoles(roles);

        agentRepository.save(agent);

        return "User Registered Successfully!.";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);


       // return "Agent logged-in successfully!.";
       // return  token;
        Optional<Agent> agentOptional = agentRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(),
                loginDto.getUsernameOrEmail());

        String role = null;
        if(agentOptional.isPresent()){
            Agent loggedInAgent = agentOptional.get();
            Optional<Role> optionalRole = loggedInAgent.getRoles().stream().findFirst();

            if(optionalRole.isPresent()){
                Role userRole = optionalRole.get();
                role = userRole.getName();
            }
        }

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setRole(role);
        jwtAuthResponse.setAccessToken(token);
        return jwtAuthResponse;
    }

    @Override
    public List<AgentDto> getAllAgents() {

            List<Agent> agentsList =  agentRepository.findAll();
            return agentsList.stream()
                    .map((agent) -> AgentMapper.toDto(agent)).collect(Collectors.toList());


    }
}