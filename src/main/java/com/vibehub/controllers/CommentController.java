package com.vibehub.controllers;

import com.vibehub.dto.CommentDto;
import com.vibehub.dto.UserDto;
import com.vibehub.payload.ApiResponse;
import com.vibehub.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){
        CommentDto createdComment = commentService.createComment(commentDto);
        return new ResponseEntity<>(createdComment,HttpStatus.CREATED);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable String postId){
        List<CommentDto> comments = commentService.getAllCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable String commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Deleted",true),HttpStatus.OK);
    }
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable String commentId, @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateComment(commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }
}
