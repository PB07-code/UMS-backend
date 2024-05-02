package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    // Build Register REST API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Build Login REST API
    @PostMapping("/login")
   /* public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    } */
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
      //  String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = authService.login(loginDto);
      //  JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
       // jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    //Build Get All Agents Rest API
    @GetMapping("/agents")
    public ResponseEntity<List<AgentDto>> getAllAgents(){
        List<AgentDto> agentsList = authService.getAllAgents();
        return ResponseEntity.ok(agentsList);
    }

}