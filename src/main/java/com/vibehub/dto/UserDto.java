package com.vibehub.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String id;
    private String name;
    private String userName;
    private String gender;
    private String bio;
    private List<PostDto> posts;
}
