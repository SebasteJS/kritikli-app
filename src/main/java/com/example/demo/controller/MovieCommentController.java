package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.logic.ResourceNotFoundException;
import com.example.demo.repository.MovieCommentRepository;
import com.example.demo.service.MovieCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieCommentController {

    private final MovieCommentService movieCommentService;
    private final MovieCommentRepository movieCommentRepository;

    @GetMapping("/main/movie/{postId}/comments")
    public String commentsPage(Model model, @PathVariable Long postId) {
        List<CommentDto> commentDtoList = movieCommentService.list();
        for (CommentDto commentDto : commentDtoList) {
            if(commentDto.getPostId().equals(postId)) {
                model.addAttribute("comment", CommentDto.builder().build());
            }
        }
        getAllComments(model, postId);
        return "movie";
    }

    @PostMapping("/main/movie/{postId}/comments")
    public String addCommentToPage(Model model, @ModelAttribute("comment") @Valid CommentDto commentDto, @PathVariable Long postId, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            movieCommentService.add(commentDto);
        }
        getAllComments(model, postId);
        return "movie";
    }

    @PutMapping("/main/movie/{postId}/comments/{commentId}")
    public String editComment(Model model, @PathVariable Long postId, @PathVariable Long commentId, @Valid CommentDto commentDto) {
        if(!movieCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(movieCommentService.getCommentById(commentId).getId().equals(commentId)) {
            movieCommentService.update(commentDto);
        }
        getAllComments(model, postId);
        return "movie";
    }

    @DeleteMapping("/main/movie/{postId}/comments/{commentId}")
    public String deleteComment(Model model, @PathVariable Long postId, @PathVariable Long commentId){
        if(!movieCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(movieCommentService.getCommentById(commentId).getId().equals(commentId)) {
            movieCommentService.delete(commentId);
        }
        getAllComments(model, postId);
        return "movie";
    }

    private void getAllComments(Model model, Long postId) {
        model.addAttribute("comments", movieCommentService.listWithSpecifiedId(postId));
    }

}
