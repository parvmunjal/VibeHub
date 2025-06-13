package com.vibehub.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostDto {
    private String userId;
    private String postId;
    private String caption;
    private String contentLink;
    private LocalDateTime timeStamp;
    private List<String> comments=new ArrayList<>();
    private List<String> likedBy=new ArrayList<>(); //userIds
}
