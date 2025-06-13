package com.vibehub.dto;

import com.vibehub.models.Story;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class StoryDto {
    private String id;
    private String userId;
    private String content;
    private LocalDateTime timestamp;
    private Set<String> viewedBy=new HashSet<>();
}
