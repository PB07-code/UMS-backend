package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        user.setIsDeleted("N");
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);


    }

    @Override
    public UserDto getUserById(Long userId) {
      User user = userRepository.findById(userId)
              .orElseThrow(()->
                      new ResourceNotFoundException("User does not exist with this id" + userId));
      return UserMapper.mapToUserDto(user);
    }


  /*  public List<UserDto> getAllUsers() {
      List<User> usersList =  userRepository.findAll();
        return usersList.stream()
                .map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());

    }*/

    //Get Active Users
  @Override
        public List<UserDto> getAllUsers() {
           /* List<User> usersList = userRepository.findAll();
            return usersList.stream()
                    .filter(user -> "N".equals(user.getIsDeleted())) // Filter out users where isDeleted is "N"
                    .map(UserMapper::mapToUserDto) // Using method reference instead of lambda
                    .collect(Collectors.toList()); */
      List<User> usersList = userRepository.findByIsDeleted("N");
      return usersList.stream().map(UserMapper::mapToUserDto) // Using method reference instead of lambda
              .collect(Collectors.toList());
        }



    @Override
    public UserDto updateUser(Long userId, UserDto updatedUser) {
        User user =  userRepository.findById(userId)
            .orElseThrow(()->new ResourceNotFoundException("User does not exist with this id" + userId));

            user.setUserAddress(updatedUser.getUserAddress());
            user.setUserDesignation(updatedUser.getUserDesignation());
            user.setUserEmail(updatedUser.getUserEmail());
            user.setUserName(updatedUser.getUserName());
            user.setUserPhone(updatedUser.getUserPhone());

      return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto deleteUser(Long userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User does not exist with this id" + userId));

       // userRepository.deleteById(userId);
        user.setIsDeleted("Y");
        return UserMapper.mapToUserDto(userRepository.save(user));


    }

    @Override
    public int getTotalUserCount() {
        List<User> usersList = userRepository.findAll();
        return usersList.size(); // Total number of users
    }

//Total Users
    @Override
    public List<UserDto> getTotalUsers() {
           /* List<User> usersList = userRepository.findAll();
            return usersList.stream()
                    .filter(user -> "N".equals(user.getIsDeleted())) // Filter out users where isDeleted is "N"
                    .map(UserMapper::mapToUserDto) // Using method reference instead of lambda
                    .collect(Collectors.toList()); */
        List<User> usersList = userRepository.findAll();
        return usersList.stream().map(UserMapper::mapToUserDto) // Using method reference instead of lambda
                .collect(Collectors.toList());
    }


    //Get Removed Users
    @Override
    public List<UserDto> getRemovedUsers() {
           /* List<User> usersList = userRepository.findAll();
            return usersList.stream()
                    .filter(user -> "N".equals(user.getIsDeleted())) // Filter out users where isDeleted is "N"
                    .map(UserMapper::mapToUserDto) // Using method reference instead of lambda
                    .collect(Collectors.toList()); */
        List<User> usersList = userRepository.findByIsDeleted("Y");
        return usersList.stream().map(UserMapper::mapToUserDto) // Using method reference instead of lambda
                .collect(Collectors.toList());
    }



}
