package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.logic.ResourceNotFoundException;
import com.example.demo.repository.PoetryCommentRepository;
import com.example.demo.service.PoetryCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PoetryCommentController {

    private final PoetryCommentService poetryCommentService;
    private final PoetryCommentRepository poetryCommentRepository;

    @GetMapping("/main/poetry/{postId}/comments")
    public String commentsPage(Model model, @PathVariable Long postId) {
        List<CommentDto> commentDtoList = poetryCommentService.list();
        for (CommentDto commentDto : commentDtoList) {
            if(commentDto.getPostId().equals(postId)) {
                model.addAttribute("comment", CommentDto.builder().build());
            }
        }
        getAllComments(model, postId);
        return "poetry";
    }

    @PostMapping("/main/poetry/{postId}/comments")
    public String addCommentToPage(Model model, @ModelAttribute("comment") @Valid CommentDto commentDto, @PathVariable Long postId, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            poetryCommentService.add(commentDto);
        }
        getAllComments(model, postId);
        return "poetry";
    }

    @PutMapping("/main/poetry/{postId}/comments/{commentId}")
    public String editComment(Model model, @PathVariable Long postId, @PathVariable Long commentId, @Valid CommentDto commentDto) {
        if(!poetryCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(poetryCommentService.getCommentById(commentId).getId().equals(commentId)) {
            poetryCommentService.update(commentDto);
        }
        getAllComments(model, postId);
        return "poetry";
    }

    @DeleteMapping("/main/poetry/{postId}/comments/{commentId}")
    public String deleteComment(Model model, @PathVariable Long postId, @PathVariable Long commentId){
        if(!poetryCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(poetryCommentService.getCommentById(commentId).getId().equals(commentId)) {
            poetryCommentService.delete(commentId);
        }
        getAllComments(model, postId);
        return "book";
    }

    private void getAllComments(Model model, Long postId) {
        model.addAttribute("comments", poetryCommentService.listWithSpecifiedId(postId));
    }

}
