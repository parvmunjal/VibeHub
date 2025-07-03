package com.vibehub.service.impl;

import com.vibehub.dto.UserDto;
import com.vibehub.exceptions.EntityNotFoundException;
import com.vibehub.models.User;
import com.vibehub.payload.MappingUtil;
import com.vibehub.repo.PostRepo;
import com.vibehub.repo.UserRepo;
import com.vibehub.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MappingUtil mappingUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mappingUtil.dtoToUser(userDto);
        logger.info("Creating user: {}",userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepo.save(user);
        return mappingUtil.userToDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map((user)->mappingUtil.userToDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(String userId) {
        logger.info("Getting user by userId: {}", userId);
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        return mappingUtil.userToDto(user);
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        logger.info("Updating user: {} with new details: {}",user,userDto);
        user.setName(userDto.getName());
        user.setUserName(userDto.getUserName());
        user.setGender(userDto.getGender());
        user.setBio(userDto.getBio());
        User savedUser = userRepo.save(user);
        return mappingUtil.userToDto(savedUser);
    }

    @Override
    public void deleteUser(String userId) {
        logger.info("Deleting user with id: {}",userId);
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        userRepo.delete(user);
    }

    @Override
    public UserDto followUser(String userId1,String userId2) {
        User user1 = userRepo.findById(userId1).orElseThrow(EntityNotFoundException::new);
        User user2 = userRepo.findById(userId2).orElseThrow(EntityNotFoundException::new);
        if(user1.getFollowings().contains(userId2)){
            user1.getFollowings().remove(userId2);
            user2.getFollowers().remove(userId1);
            logger.info("{} unfollowed {}",user1,user2);
        }
        else{
            user1.getFollowings().add(userId2);
            user2.getFollowers().add(userId1);
            logger.info("{} followed {}",user1,user2);
        }

        userRepo.save(user1);
        userRepo.save(user2);
        return mappingUtil.userToDto(user1);
    }

    @Override
    public List<UserDto> getFollowersByUserId(String userId) {
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<String> followers = user.getFollowers();
        List<User> users = userRepo.findAllById(followers);
        return users.stream().map((u)->mappingUtil.userToDto(u)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getFollowingsByUserId(String userId) {
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<String> followings = user.getFollowings();
        List<User> users = userRepo.findAllById(followings);
        return users.stream().map((u)->mappingUtil.userToDto(u)).collect(Collectors.toList());
    }
}



