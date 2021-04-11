package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.logic.ResourceNotFoundException;
import com.example.demo.repository.ImageCommentRepository;
import com.example.demo.service.ImageCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ImageCommentController {

    private final ImageCommentService imageCommentService;
    private final ImageCommentRepository imageCommentRepository;

    @GetMapping("/main/image/{postId}/comments")
    public String commentsPage(Model model, @PathVariable Long postId) {
        List<CommentDto> commentDtoList = imageCommentService.list();
        for (CommentDto commentDto : commentDtoList) {
            if(commentDto.getPostId().equals(postId)) {
                model.addAttribute("comment", CommentDto.builder().build());
            }
        }
        getAllComments(model, postId);
        return "image";
    }

    @PostMapping("/main/image/{postId}/comments")
    public String addCommentToPage(Model model, @ModelAttribute("comment") @Valid CommentDto commentDto, @PathVariable Long postId, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            imageCommentService.add(commentDto);
        }
        getAllComments(model, postId);
        return "image";
    }

    @PutMapping("/main/image/{postId}/comments/{commentId}")
    public String editComment(Model model, @PathVariable Long postId, @PathVariable Long commentId, @Valid CommentDto commentDto) {
        if(!imageCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(imageCommentService.getCommentById(commentId).getId().equals(commentId)) {
            imageCommentService.update(commentDto);
        }
        getAllComments(model, postId);
        return "image";
    }

    @DeleteMapping("/main/image/{postId}/comments/{commentId}")
    public String deleteComment(Model model, @PathVariable Long postId, @PathVariable Long commentId){
        if(!imageCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(imageCommentService.getCommentById(commentId).getId().equals(commentId)) {
            imageCommentService.delete(commentId);
        }
        getAllComments(model, postId);
        return "image";
    }

    private void getAllComments(Model model, Long postId) {
        model.addAttribute("comments", imageCommentService.listWithSpecifiedId(postId));
    }

}
