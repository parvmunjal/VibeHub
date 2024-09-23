package com.vibehub.service;

import com.vibehub.dto.UserDto;

import java.util.List;

public interface UserService {
    //create user
    UserDto createUser(UserDto userDto);
    //get all users
    List<UserDto> getAllUsers();
    //get user by id
    UserDto getUserById(String userId);
    //update user
    UserDto updateUser(String userId,UserDto userDto);
    //delete user
    void deleteUser(String userId);
}
