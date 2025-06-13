package com.vibehub.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "stories")
@Data
public class Story {
    @Id
    private String id;
    private String userId;
    private String content;
    private LocalDateTime timestamp;
    private Set<String> viewedBy=new HashSet<>();
    private boolean status;
}
