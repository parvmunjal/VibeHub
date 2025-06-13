package com.vibehub.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private String id;
    private String userId;
    private String postId;
    private String comment;
    private LocalDateTime timeStamp;
}
