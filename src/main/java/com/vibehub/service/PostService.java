package com.vibehub.service;

import com.vibehub.dto.PostDto;

import java.util.List;

public interface PostService {
    //create post
    PostDto createPost(PostDto postDto);
    //update post
    PostDto updatePost(String postId,PostDto postDto);
    //delete post
    void deletePost(String postId);
    //get post by id
    PostDto getPostById(String postId);
    //get all posts
    List<PostDto> getAllPosts();
    //get all posts by userId
    List<PostDto> getAllPostsByUserId(String userId);
}
