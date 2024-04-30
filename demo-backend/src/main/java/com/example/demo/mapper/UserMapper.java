package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

//userId, userName, userEmail, userPhone, userAddress, userDesignation
public class UserMapper {
    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserPhone(),
                user.getUserAddress(),
                user.getUserDesignation(),
                user.getIsDeleted()
        );

    }

    public static User mapToUser(UserDto UserDto){
        return new User(
                UserDto.getUserId(),
                UserDto.getUserName(),
                UserDto.getUserEmail(),
                UserDto.getUserPhone(),
                UserDto.getUserAddress(),
                UserDto.getUserDesignation(),
                UserDto.getIsDeleted()
        );
    }
}
