package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.logic.ResourceNotFoundException;
import com.example.demo.repository.BookCommentRepository;
import com.example.demo.service.BookCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookCommentController {

    private final BookCommentService bookCommentService;
    private final BookCommentRepository bookCommentRepository;

    @GetMapping("/main/book/{postId}/comments")
    public String commentsPage(Model model, @PathVariable Long postId) {
        List<CommentDto> commentDtoList = bookCommentService.list();
        for (CommentDto commentDto : commentDtoList) {
            if(commentDto.getPostId().equals(postId)) {
                model.addAttribute("comment", CommentDto.builder().build());
            }
        }
        getAllComments(model, postId);
        return "book";
    }

    @PostMapping("/main/book/{postId}/comments")
    public String addCommentToPage(Model model, @ModelAttribute("comment") @Valid CommentDto commentDto, @PathVariable Long postId, BindingResult bindingResult) {
            if(!bindingResult.hasErrors()) {
                bookCommentService.add(commentDto);
            }
        getAllComments(model, postId);
        return "book";
    }

    @PutMapping("/main/book/{postId}/comments/{commentId}")
    public String editComment(Model model, @PathVariable Long postId, @PathVariable Long commentId, @Valid CommentDto commentDto) {
        if(!bookCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(bookCommentService.getCommentById(commentId).getId().equals(commentId)) {
            bookCommentService.update(commentDto);
        }
        getAllComments(model, postId);
        return "book";
    }

    @DeleteMapping("/main/book/{postId}/comments/{commentId}")
    public String deleteComment(Model model, @PathVariable Long postId, @PathVariable Long commentId){
        if(!bookCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(bookCommentService.getCommentById(commentId).getId().equals(commentId)) {
            bookCommentService.delete(commentId);
        }
        getAllComments(model, postId);
        return "book";
    }

    private void getAllComments(Model model, Long postId) {
        model.addAttribute("comments", bookCommentService.listWithSpecifiedId(postId));
    }

}
