package com.vibehub.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "posts")
@Data
public class Post {
    @Id
    private String postId;
    private String caption;
    private String contentLink;
    private LocalDateTime timeStamp;
    private String userId;
    private List<String> comments=new ArrayList<>();
    private List<String> likedBy=new ArrayList<>();
}
