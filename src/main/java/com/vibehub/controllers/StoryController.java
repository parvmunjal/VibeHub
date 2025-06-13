package com.vibehub.controllers;

import com.vibehub.dto.StoryDto;
import com.vibehub.payload.ApiResponse;
import com.vibehub.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story")
public class StoryController {
    @Autowired
    private StoryService storyService;

    @PostMapping
    public ResponseEntity<StoryDto> createStory(@RequestBody StoryDto storyDto){
        StoryDto createdStory = storyService.createStory(storyDto);
        return new ResponseEntity<>(createdStory, HttpStatus.CREATED);
    }
    @PutMapping("/{storyId}")
    public ResponseEntity<StoryDto> updateStory(@PathVariable String storyId,@RequestBody StoryDto storyDto){
        StoryDto updatedStory = storyService.updateStory(storyId,storyDto);
        return new ResponseEntity<>(updatedStory, HttpStatus.OK);
    }
    @PatchMapping("/{userId}/{storyId}")
    public ResponseEntity<StoryDto> viewStory(@PathVariable String userId,@PathVariable String storyId){
        StoryDto story = storyService.viewStory(userId, storyId);
        return ResponseEntity.ok(story);
    }
    @DeleteMapping("/{storyId}")
    public ResponseEntity<ApiResponse> deleteStory(@PathVariable String storyId){
        storyService.deleteStory(storyId);
        return new ResponseEntity<>(new ApiResponse("Story Deleted",true),HttpStatus.OK);
    }
    //get stories by userId
    @GetMapping("/{userId}")
    public ResponseEntity<List<StoryDto>> getStoryByUserId(@PathVariable String userId){
        List<StoryDto> stories = storyService.findAllByUserId(userId);
        return ResponseEntity.ok(stories);
    }

}
