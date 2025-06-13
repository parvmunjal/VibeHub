package com.vibehub.repo;

import com.vibehub.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepo extends MongoRepository<Comment,String> {
    List<Comment> findAllByPostId(String postId);
}
