package com.example.demo.mapper;

import com.example.demo.dto.AgentDto;
import com.example.demo.entity.Agent;

import java.util.stream.Collectors;

public class AgentMapper {

    public static AgentDto toDto(Agent agent) {
        AgentDto dto = new AgentDto();
        dto.setId(agent.getId());
        dto.setName(agent.getName());
        dto.setUsername(agent.getUsername());
        dto.setEmail(agent.getEmail());
        dto.setPassword(agent.getPassword());
        dto.setRoles(agent.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()));
        return dto;
    }

    public static Agent toEntity(AgentDto dto) {
        Agent agent = new Agent();
        agent.setId(dto.getId());
        agent.setName(dto.getName());
        agent.setUsername(dto.getUsername());
        agent.setEmail(dto.getEmail());
        agent.setPassword(dto.getPassword());
        // Assuming roles are not mapped in reverse from DTO to Entity
        return agent;
    }
}
