package com.vibehub.repo;

import com.vibehub.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepo extends MongoRepository<Post,String> {
    public List<Post> findAllByUserId(String userId);
}
