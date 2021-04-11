package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.logic.ResourceNotFoundException;
import com.example.demo.repository.FashionCommentRepository;
import com.example.demo.service.FashionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FashionCommentController {

    private final FashionCommentService fashionCommentService;
    private final FashionCommentRepository fashionCommentRepository;

    @GetMapping("/main/fashion/{postId}/comments")
    public String commentsPage(Model model, @PathVariable Long postId) {
        List<CommentDto> commentDtoList = fashionCommentService.list();
        for (CommentDto commentDto : commentDtoList) {
            if(commentDto.getPostId().equals(postId)) {
                model.addAttribute("comment", CommentDto.builder().build());
            }
        }
        getAllComments(model, postId);
        return "fashion";
    }

    @PostMapping("/main/fashion/{postId}/comments")
    public String addCommentToPage(Model model, @ModelAttribute("comment") @Valid CommentDto commentDto, @PathVariable Long postId, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            fashionCommentService.add(commentDto);
        }
        getAllComments(model, postId);
        return "fashion";
    }

    @PutMapping("/main/fashion/{postId}/comments/{commentId}")
    public String editComment(Model model, @PathVariable Long postId, @PathVariable Long commentId, @Valid CommentDto commentDto) {
        if(!fashionCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(fashionCommentService.getCommentById(commentId).getId().equals(commentId)) {
            fashionCommentService.update(commentDto);
        }
        getAllComments(model, postId);
        return "fashion";
    }

    @DeleteMapping("/main/fashion/{postId}/comments/{commentId}")
    public String deleteComment(Model model, @PathVariable Long postId, @PathVariable Long commentId){
        if(!fashionCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(fashionCommentService.getCommentById(commentId).getId().equals(commentId)) {
            fashionCommentService.delete(commentId);
        }
        getAllComments(model, postId);
        return "fashion";
    }

    private void getAllComments(Model model, Long postId) {
        model.addAttribute("comments", fashionCommentService.listWithSpecifiedId(postId));
    }

}
