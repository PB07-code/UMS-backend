package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.Data;

@Data
public class NotificationDto {
    private String message;
    private UserDto userDto;


}


