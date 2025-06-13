package com.vibehub.repo;

import com.vibehub.models.Story;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StoryRepo extends MongoRepository<Story,String> {
    List<Story> findByUserId(String userId);
    List<Story> findAllByUserIdAndStatusTrue(String userId);
}
