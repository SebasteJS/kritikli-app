package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.logic.ResourceNotFoundException;
import com.example.demo.repository.MusicCommentRepository;
import com.example.demo.service.MusicCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MusicCommentController {

    private final MusicCommentService musicCommentService;
    private final MusicCommentRepository musicCommentRepository;

    @GetMapping("/main/music/{postId}/comments")
    public String commentsPage(Model model, @PathVariable Long postId) {
        List<CommentDto> commentDtoList = musicCommentService.list();
        for (CommentDto commentDto : commentDtoList) {
            if (commentDto.getPostId().equals(postId)) {
                model.addAttribute("comment", CommentDto.builder().build());
            }
        }
        getAllComments(model, postId);
        return "music";
    }

    @PostMapping("/main/music/{postId}/comments")
    public String addCommentToPage(Model model, @ModelAttribute("comment") @Valid CommentDto commentDto, @PathVariable Long postId, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            musicCommentService.add(commentDto);
        }
        getAllComments(model, postId);
        return "music";
    }

    @PutMapping("/main/music/{postId}/comments/{commentId}")
    public String editComment(Model model, @PathVariable Long postId, @PathVariable Long commentId, @Valid CommentDto commentDto) {
        if (!musicCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if (musicCommentService.getCommentById(commentId).getId().equals(commentId)) {
            musicCommentService.update(commentDto);
        }
        getAllComments(model, postId);
        return "music";
    }

    @DeleteMapping("/main/music/{postId}/comments/{commentId}")
    public String deleteComment(Model model, @PathVariable Long postId, @PathVariable Long commentId) {
        if (!musicCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if (musicCommentService.getCommentById(commentId).getId().equals(commentId)) {
            musicCommentService.delete(commentId);
        }
        getAllComments(model, postId);
        return "music";
    }

    private void getAllComments(Model model, Long postId) {
        model.addAttribute("comments", musicCommentService.listWithSpecifiedId(postId));
    }

}
