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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookCommentController {

    private final BookCommentService bookCommentService;
    private final BookCommentRepository bookCommentRepository;

    @GetMapping("/main/book/{postId}/comments")
    public String bookCommentsPage(Model model, @PathVariable Long postId) {
        List<CommentDto> commentDtoList = bookCommentService.list();
        for (CommentDto commentDto : commentDtoList) {
            if(commentDto.getPostId().equals(postId)) {
                model.addAttribute("bookComment", CommentDto.builder().build());
            }
        }
        getAllBookComments(model, postId);
        return "book";
    }

    @PostMapping("/main/book/{postId}/comments")
    public String addBookCommentToPage(Model model, @ModelAttribute("bookComment") @Valid CommentDto bookComment, @PathVariable Long postId) {
        List<CommentDto> commentDtoList = bookCommentService.list();
        for (CommentDto commentDto : commentDtoList) {
            if(commentDto.getPostId().equals(postId)) {
                bookCommentService.add(bookComment);
            } else {
                throw new ResourceNotFoundException("PostId " + postId + " not found");
            }
        }
        getAllBookComments(model, postId);
        return "book";
    }

    @PutMapping("/main/book/{postId}/comments/{commentId}")
    public String editBookComment(Model model, @PathVariable Long postId, @PathVariable Long commentId, @Valid CommentDto bookComment, BindingResult bindingResult) {
        if(!bookCommentRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        getAllBookComments(model, postId);
        return "book";
    }

    @DeleteMapping("/main/book/{postId}/comments/{commentId}")
    public String deleteBookComment(){

        return "book";
    }

    private void getAllBookComments(Model model, Long bookId) {
        model.addAttribute("bookComments", bookCommentService.listWithSpecifiedId(bookId));
    }

}
