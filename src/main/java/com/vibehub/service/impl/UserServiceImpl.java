package com.vibehub.service.impl;

import com.vibehub.dto.UserDto;
import com.vibehub.models.User;
import com.vibehub.repo.UserRepo;
import com.vibehub.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return userToDto(user);
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        User user = userRepo.findById(userId).orElseThrow();
        user.setName(userDto.getName());
        user.setUserName(userDto.getUserName());
        user.setGender(userDto.getGender());
        user.setBio(userDto.getBio());
        User savedUser = userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepo.findById(userId).orElseThrow();
        userRepo.delete(user);
    }
    //dto to user
    private User dtoToUser(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }
    //user to dto
    private UserDto userToDto(User user){
        return modelMapper.map(user,UserDto.class);
    }
}

