package com.vibehub.payload;

import com.vibehub.dto.PostDto;
import com.vibehub.dto.UserDto;
import com.vibehub.models.Post;
import com.vibehub.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappingUtil {
    private final ModelMapper modelMapper;

    @Autowired
    public MappingUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // User Mappings
    public User dtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto userToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    // Post Mappings
    public Post dtoToPost(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

    public PostDto postToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }
}
