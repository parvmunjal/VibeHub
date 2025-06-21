package com.vibehub.service.impl;

import com.vibehub.dto.CommentDto;
import com.vibehub.dto.PostDto;
import com.vibehub.dto.UserDto;
import com.vibehub.exceptions.EntityNotFoundException;
import com.vibehub.models.Post;
import com.vibehub.models.User;
import com.vibehub.payload.MappingUtil;
import com.vibehub.repo.CommentRepo;
import com.vibehub.repo.PostRepo;
import com.vibehub.repo.UserRepo;
import com.vibehub.service.PostService;
import com.vibehub.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MappingUtil mappingUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentRepo commentRepo;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mappingUtil.dtoToPost(postDto);
        logger.info("Creating post: {}",postDto);
        post.setTimeStamp(LocalDateTime.now());
        Post savedPost = postRepo.save(post);

        String userId = postDto.getUser().getId();
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.getPosts().add(savedPost.getPostId());
        userRepo.save(user);

        return mappingUtil.postToDto(savedPost);
    }

    @Override
    public PostDto updatePost(String postId, PostDto postDto) {
        Post post = postRepo.findById(postId).orElseThrow(EntityNotFoundException::new);
        logger.info("Updating post: {} with new details: {}",post,postDto);
        post.setCaption(postDto.getCaption());
        post.setContentLink(postDto.getContentLink());
        Post savedPost = postRepo.save(post);
        return mappingUtil.postToDto(savedPost);
    }

    @Override
    public void deletePost(String postId) {
        Post post = postRepo.findById(postId).orElseThrow(EntityNotFoundException::new);
        logger.info("Deleting post with postId: {}",postId);
        postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(String postId) {
        Post post = postRepo.findById(postId).orElseThrow(EntityNotFoundException::new);
        logger.info("Getting posts by postId: {}",postId);
        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setCaption(post.getCaption());
        postDto.setTimeStamp(post.getTimeStamp());
        postDto.setContentLink(post.getContentLink());
        postDto.setUser(userService.getUserById(post.getUserId()));
        List<UserDto> likedByDtos = new ArrayList<>();
        for (String likerId : post.getLikedBy()) {
            UserDto likerDto = userService.getUserById(likerId);
            likedByDtos.add(likerDto);
        }
        postDto.setLikedBy(likedByDtos);
        postDto.setUser(userService.getUserById(post.getUserId()));
        List<CommentDto> comments=new ArrayList<>();
        for (String commentId: post.getComments()){
            comments.add(mappingUtil.commentToDto(commentRepo.findById(commentId).orElseThrow(EntityNotFoundException::new)));
        }
        postDto.setComments(comments);
        return postDto;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(post -> {
            PostDto postDto = new PostDto();
            postDto.setPostId(post.getPostId());
            postDto.setCaption(post.getCaption());
            postDto.setTimeStamp(post.getTimeStamp());
            postDto.setContentLink(post.getContentLink());
            postDto.setUser(userService.getUserById(post.getUserId()));
            List<UserDto> likedByDtos = new ArrayList<>();
            for (String likerId : post.getLikedBy()) {
                UserDto likerDto = userService.getUserById(likerId);
                likedByDtos.add(likerDto);
            }
            postDto.setLikedBy(likedByDtos);
            List<CommentDto> comments=new ArrayList<>();
            for (String commentId: post.getComments()){
                comments.add(mappingUtil.commentToDto(commentRepo.findById(commentId).orElseThrow(EntityNotFoundException::new)));
            }
            postDto.setComments(comments);
            return postDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByUserId(String userId) {
        List<Post> posts = postRepo.findAllByUserId(userId);
        logger.info("Getting posts by userId: {}", userId);

        return posts.stream().map(post -> {
            PostDto postDto = new PostDto();
            postDto.setPostId(post.getPostId());
            postDto.setCaption(post.getCaption());
            postDto.setTimeStamp(post.getTimeStamp());
            postDto.setContentLink(post.getContentLink());
            postDto.setUser(userService.getUserById(post.getUserId()));
            List<UserDto> likedByDtos = new ArrayList<>();
            for (String likerId : post.getLikedBy()) {
                UserDto likerDto = userService.getUserById(likerId);
                likedByDtos.add(likerDto);
            }
            postDto.setLikedBy(likedByDtos);
            List<CommentDto> comments=new ArrayList<>();
            for (String commentId: post.getComments()){
                comments.add(mappingUtil.commentToDto(commentRepo.findById(commentId).orElseThrow(EntityNotFoundException::new)));
            }
            postDto.setComments(comments);
            return postDto;
        }).collect(Collectors.toList());
    }


    @Override
    public PostDto likePost(String postId, String userId) {
        Post post = postRepo.findById(postId).orElseThrow(EntityNotFoundException::new);
        if(post.getLikedBy().contains(userId)){
            post.getLikedBy().remove(userId);
            logger.info("User with userId: {} unlikes post with postId: {}",userId,postId);
        }
        else{
            post.getLikedBy().add(userId);
            logger.info("User with userId: {} likes post with postId: {}",userId,postId);
        }

        Post savedPost = postRepo.save(post);
        PostDto postDto =  new PostDto();
        postDto.setPostId(savedPost.getPostId());
        postDto.setCaption(savedPost.getCaption());
        postDto.setTimeStamp(savedPost.getTimeStamp());
        postDto.setContentLink(savedPost.getContentLink());
        postDto.setUser(userService.getUserById(post.getUserId()));
        List<UserDto> likedByDtos = new ArrayList<>();
        for (String likerId : savedPost.getLikedBy()) {
            UserDto likerDto = userService.getUserById(likerId);
            likedByDtos.add(likerDto);
        }
        postDto.setLikedBy(likedByDtos);
        List<CommentDto> comments=new ArrayList<>();
        for (String commentId: post.getComments()){
            comments.add(mappingUtil.commentToDto(commentRepo.findById(commentId).orElseThrow(EntityNotFoundException::new)));
        }
        postDto.setComments(comments);
        return postDto;
    }

    @Override
    public List<List<PostDto>> getAllPostsOfFollowings(String userId) {
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<String> userIds = user.getFollowings();
        return findAllByFollowings(userIds);
    }
    private List<List<PostDto>> findAllByFollowings(List<String> userIds){
        List<List<PostDto>> allPostsByFollowings=new ArrayList<>();
        for(String userId:userIds){
            List<PostDto> posts = findAllPosts(userId);
            if(!posts.isEmpty()){
                allPostsByFollowings.add(posts);
            }
        }
        return allPostsByFollowings;
    }
    private List<PostDto> findAllPosts(String userId){
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<Post> posts = postRepo.findAllByUserIdOrderByTimeStampDesc(userId);
        return posts.stream().map(post -> {
            PostDto postDto = new PostDto();
            postDto.setPostId(post.getPostId());
            postDto.setCaption(post.getCaption());
            postDto.setTimeStamp(post.getTimeStamp());
            postDto.setContentLink(post.getContentLink());
            postDto.setUser(userService.getUserById(post.getUserId()));
            List<UserDto> likedByDtos = new ArrayList<>();
            for (String likerId : post.getLikedBy()) {
                UserDto likerDto = userService.getUserById(likerId);
                likedByDtos.add(likerDto);
            }
            postDto.setLikedBy(likedByDtos);
            List<CommentDto> comments=new ArrayList<>();
            for (String commentId: post.getComments()){
                comments.add(mappingUtil.commentToDto(commentRepo.findById(commentId).orElseThrow(EntityNotFoundException::new)));
            }
            postDto.setComments(comments);
            return postDto;
        }).collect(Collectors.toList());
    }
}


