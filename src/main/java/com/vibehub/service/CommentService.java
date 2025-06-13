package com.vibehub.service;

import com.vibehub.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto);
    List<CommentDto> getAllCommentsByPostId(String postId);
    void deleteComment(String commentId);
    CommentDto updateComment(String commentId,CommentDto commentDto);
}
