package com.vibehub.service.impl;

import com.vibehub.dto.StoryDto;
import com.vibehub.exceptions.EntityNotFoundException;
import com.vibehub.models.Story;
import com.vibehub.models.User;
import com.vibehub.payload.MappingUtil;
import com.vibehub.repo.StoryRepo;
import com.vibehub.repo.UserRepo;
import com.vibehub.service.StoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryServiceImpl implements StoryService {
    private static final Logger logger = LoggerFactory.getLogger(StoryServiceImpl.class);
    @Autowired
    private StoryRepo storyRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MappingUtil mappingUtil;


    @Override
    public StoryDto createStory(StoryDto storyDto) {
        Story story = mappingUtil.dtoToStory(storyDto);

        story.setTimestamp(LocalDateTime.now());
        logger.info("Creating story: {}",storyDto);
        Story savedStory = storyRepo.save(story);

        String userId = storyDto.getUserId();
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.getStories().add(savedStory.getId());
        userRepo.save(user);

        return mappingUtil.storyToDto(savedStory);
    }

    @Override
    public StoryDto viewStory(String userId, String storyId) {
        Story story = storyRepo.findById(storyId).orElseThrow(EntityNotFoundException::new);
        logger.info("User with userId: {} viewing story with storyId: {}",userId,storyId);
        story.getViewedBy().add(userId);

        Story savedStory = storyRepo.save(story);
        return mappingUtil.storyToDto(savedStory);
    }

    @Override
    public StoryDto updateStory(String storyId, StoryDto storyDto) {
        Story story = storyRepo.findById(storyId).orElseThrow(EntityNotFoundException::new);
        logger.info("Updating story with storyId: {}",storyId);
        story.setContent(storyDto.getContent());
        Story updatedStory=storyRepo.save(story);
        return mappingUtil.storyToDto(updatedStory);
    }

    @Override
    public void deleteStory(String storyId) {
        Story story = storyRepo.findById(storyId).orElseThrow(EntityNotFoundException::new);
        logger.info("Deleting story with storyId: {}",storyId);
        storyRepo.delete(story);
    }

    @Override
    public List<StoryDto> findAllByUserId(String userId) {
        List<Story> stories = storyRepo.findByUserId(userId);
        return stories.stream().map((story) -> mappingUtil.storyToDto(story)).toList();
    }
}
