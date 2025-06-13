package com.vibehub.repo;

import com.vibehub.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepo extends MongoRepository<Post,String> {
    List<Post> findAllByUserId(String userId);
    List<Post> findAllByUserIdOrderByTimeStampDesc(String userId);
}
