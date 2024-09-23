package com.vibehub.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private String userId;
    private String postId;
    private String caption;
    private String contentLink;
    private LocalDateTime timeStamp;
}
