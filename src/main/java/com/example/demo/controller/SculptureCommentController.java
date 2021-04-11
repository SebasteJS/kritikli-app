package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.logic.ResourceNotFoundException;
import com.example.demo.repository.SculptureCommentRepository;
import com.example.demo.service.SculptureCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SculptureCommentController {

    private final SculptureCommentService sculptureCommentService;
    private final SculptureCommentRepository sculptureCommentRepository;

    @GetMapping("/main/sculpture/{postId}/comments")
    public String commentsPage(Model model, @PathVariable Long postId) {
        List<CommentDto> commentDtoList = sculptureCommentService.list();
        for (CommentDto commentDto : commentDtoList) {
            if(commentDto.getPostId().equals(postId)) {
                model.addAttribute("comment", CommentDto.builder().build());
            }
        }
        getAllComments(model, postId);
        return "sculpture";
    }

    @PostMapping("/main/sculpture/{postId}/comments")
    public String addSculptureCommentToPage(Model model, @ModelAttribute("comment") @Valid CommentDto commentDto, @PathVariable Long postId, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            sculptureCommentService.add(commentDto);
        }
        getAllComments(model, postId);
        return "sculpture";
    }

    @PutMapping("/main/sculpture/{postId}/comments/{commentId}")
    public String editSculptureComment(Model model, @PathVariable Long postId, @PathVariable Long commentId, @Valid CommentDto commentDto) {
        if(!sculptureCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(sculptureCommentService.getCommentById(commentId).getId().equals(commentId)) {
            sculptureCommentService.update(commentDto);
        }
        getAllComments(model, postId);
        return "sculpture";
    }

    @DeleteMapping("/main/sculpture/{postId}/comments/{commentId}")
    public String deleteComment(Model model, @PathVariable Long postId, @PathVariable Long commentId){
        if(!sculptureCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(sculptureCommentService.getCommentById(commentId).getId().equals(commentId)) {
            sculptureCommentService.delete(commentId);
        }
        getAllComments(model, postId);
        return "sculpture";
    }

    private void getAllComments(Model model, Long postId) {
        model.addAttribute("comments", sculptureCommentService.listWithSpecifiedId(postId));
    }

}
