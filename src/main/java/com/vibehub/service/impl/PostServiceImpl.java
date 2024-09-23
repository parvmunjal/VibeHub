package com.vibehub.service.impl;

import com.vibehub.dto.PostDto;
import com.vibehub.models.Post;
import com.vibehub.repo.PostRepo;
import com.vibehub.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepo postRepo;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = dtoToPost(postDto);
        post.setTimeStamp(LocalDateTime.now());
        Post savedPost = postRepo.save(post);
        return postToDto(savedPost);
    }

    @Override
    public PostDto updatePost(String postId, PostDto postDto) {
        Post post = postRepo.findById(postId).orElseThrow();
        post.setCaption(postDto.getCaption());
        post.setContentLink(postDto.getContentLink());
        Post savedPost = postRepo.save(post);
        return postToDto(savedPost);
    }

    @Override
    public void deletePost(String postId) {
        Post post = postRepo.findById(postId).orElseThrow();
        postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(String postId) {
        Post post = postRepo.findById(postId).orElseThrow();
        return postToDto(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(this::postToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByUserId(String userId) {
        List<Post> posts = postRepo.findAllByUserId(userId);
        return posts.stream().map(this::postToDto).collect(Collectors.toList());
    }


    public Post dtoToPost(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

    public PostDto postToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }
}


