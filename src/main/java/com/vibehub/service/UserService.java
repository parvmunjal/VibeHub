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
    //follow user
    UserDto followUser(String userId1,String userId2);// userid1 follows userid2
    //get user followers
    List<UserDto> getFollowersByUserId(String userId);
    //get user followings
    List<UserDto> getFollowingsByUserId(String userId);
}
