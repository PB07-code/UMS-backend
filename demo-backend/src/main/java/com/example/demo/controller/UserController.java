package com.example.demo.controller;

import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    // Build Add User REST API
 //   @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<NotificationDto> createUser(@RequestBody UserDto userDto){
        UserDto savedEmployee = userService.createUser(userDto);
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setMessage("Added");
        notificationDto.setUserDto(savedEmployee);
        return new ResponseEntity(notificationDto, HttpStatus.CREATED);
    }

    //Build GetUserById RestAPI
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
      UserDto userDto=  userService.getUserById(userId);
      return ResponseEntity.ok(userDto);
    }

    //Build Get All Users Rest API
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
       List<UserDto> usersList = userService.getAllUsers();
        return ResponseEntity.ok(usersList);
    }


    //Build Update User By Id RestAPI
 //   @PreAuthorize("hasRole('ADMIN')")
  /*  @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable("id") Long userId,@RequestBody UserDto userDto){
        UserDto updateduserDto=  userService.updateUser(userId,userDto);
        return ResponseEntity.ok(updateduserDto);
    }*/

    @PutMapping("{id}")
    public ResponseEntity<NotificationDto> updateUserById(@PathVariable("id") Long userId,@RequestBody UserDto userDto) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setMessage("Updated");
        UserDto updateduserDto = userService.updateUser(userId, userDto);
        notificationDto.setUserDto(updateduserDto);
        return ResponseEntity.ok(notificationDto);
    }
    //Build Delete User By Id RestAPI

  //  @PreAuthorize("hasRole('ADMIN')")
  /*  @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User Deleted Successfully");
    }*/

     @DeleteMapping("{id}")
    public ResponseEntity<NotificationDto> deleteUserById(@PathVariable("id") Long userId){
         NotificationDto notificationDto = new NotificationDto();
         notificationDto.setMessage("Deleted");
         UserDto userDto=  userService.getUserById(userId);
         notificationDto.setUserDto(userDto);
        userService.deleteUser(userId);
        return ResponseEntity.ok(notificationDto);
    }

//GetTotalUsers
    @GetMapping("/getTotalUsers")
    public ResponseEntity<List<UserDto>> getTotalUsers(){
        List<UserDto> usersList = userService.getTotalUsers();
        return ResponseEntity.ok(usersList);
    }


    //GetRemovedUsers
    @GetMapping("/getRemovedUsers")
    public ResponseEntity<List<UserDto>> getRemovedUsers(){
        List<UserDto> usersList = userService.getRemovedUsers();
        return ResponseEntity.ok(usersList);
    }


}
