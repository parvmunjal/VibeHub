package com.vibehub.service;

import com.vibehub.dto.StoryDto;

import java.util.List;

public interface StoryService {
    StoryDto createStory(StoryDto storyDto);
    StoryDto viewStory(String userId,String storyId);
    StoryDto updateStory(String storyId,StoryDto storyDto);
    void deleteStory(String storyId);
    List<StoryDto> findAllByUserId(String userId);
    List<List<StoryDto>> findAllStoriesOfFollowings(String userId);
}
