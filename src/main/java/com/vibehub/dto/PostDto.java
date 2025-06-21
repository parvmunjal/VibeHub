package com.vibehub.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostDto {
    private String postId;
    private String caption;
    private String contentLink;
    private LocalDateTime timeStamp;
    private UserDto user;
    private List<String> comments=new ArrayList<>();
    private List<UserDto> likedBy=new ArrayList<>();
}
