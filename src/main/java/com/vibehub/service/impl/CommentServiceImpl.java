package com.vibehub.service.impl;

import com.vibehub.dto.CommentDto;
import com.vibehub.exceptions.EntityNotFoundException;
import com.vibehub.models.Comment;
import com.vibehub.models.Post;
import com.vibehub.models.User;
import com.vibehub.payload.MappingUtil;
import com.vibehub.repo.CommentRepo;
import com.vibehub.repo.PostRepo;
import com.vibehub.service.CommentService;
import com.vibehub.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private MappingUtil mappingUtil;
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = mappingUtil.dtoToComment(commentDto);
        logger.info("Creating comment: {}",commentDto);
        comment.setTimeStamp(LocalDateTime.now());

        Comment savedComment = commentRepo.save(comment);

        String postId = commentDto.getPostId();
        Post post = postRepo.findById(postId).orElseThrow(EntityNotFoundException::new);
        String commentId = savedComment.getId();
        post.getComments().add(commentId);
        postRepo.save(post);

        return mappingUtil.commentToDto(savedComment);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(String postId) {
        List<Comment> comments = commentRepo.findAllByPostId(postId);
        logger.info("Getting comments by postId: {}",postId);
        return comments.stream().map((comment)-> {
            CommentDto commentDto=mappingUtil.commentToDto(comment);
            commentDto.setUser(userService.getUserById(comment.getUserId()));
            return commentDto;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(String commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(EntityNotFoundException::new);
        logger.info("Deleting comment by commentId: {}",commentId);
        commentRepo.delete(comment);
    }

    @Override
    public CommentDto updateComment(String commentId, CommentDto commentDto) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(EntityNotFoundException::new);
        comment.setComment(commentDto.getComment());
        Comment updatedComment = commentRepo.save(comment);
        return mappingUtil.commentToDto(updatedComment);
    }
}
