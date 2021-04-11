package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.logic.ResourceNotFoundException;
import com.example.demo.repository.OtherCommentRepository;
import com.example.demo.service.OtherCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OtherCommentController {

    private final OtherCommentService otherCommentService;
    private final OtherCommentRepository otherCommentRepository;

    @GetMapping("/main/other/{postId}/comments")
    public String commentsPage(Model model, @PathVariable Long postId) {
        List<CommentDto> commentDtoList = otherCommentService.list();
        for (CommentDto commentDto : commentDtoList) {
            if(commentDto.getPostId().equals(postId)) {
                model.addAttribute("comment", CommentDto.builder().build());
            }
        }
        getAllComments(model, postId);
        return "other";
    }

    @PostMapping("/main/other/{postId}/comments")
    public String addCommentToPage(Model model, @ModelAttribute("comment") @Valid CommentDto commentDto, @PathVariable Long postId, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            otherCommentService.add(commentDto);
        }
        getAllComments(model, postId);
        return "other";
    }

    @PutMapping("/main/other/{postId}/comments/{commentId}")
    public String editComment(Model model, @PathVariable Long postId, @PathVariable Long commentId, @Valid CommentDto commentDto) {
        if(!otherCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(otherCommentService.getCommentById(commentId).getId().equals(commentId)) {
            otherCommentService.update(commentDto);
        }
        getAllComments(model, postId);
        return "other";
    }

    @DeleteMapping("/main/other/{postId}/comments/{commentId}")
    public String deleteComment(Model model, @PathVariable Long postId, @PathVariable Long commentId){
        if(!otherCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(otherCommentService.getCommentById(commentId).getId().equals(commentId)) {
            otherCommentService.delete(commentId);
        }
        getAllComments(model, postId);
        return "other";
    }

    private void getAllComments(Model model, Long postId) {
        model.addAttribute("comments", otherCommentService.listWithSpecifiedId(postId));
    }

}
