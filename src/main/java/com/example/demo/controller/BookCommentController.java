package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
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

    @GetMapping("/main/book/{postId}")
    public String bookCommentsPage(Model model, @PathVariable Long postId) {
        List<CommentDto> commentDtoList = bookCommentService.list();
        for (CommentDto commentDto : commentDtoList) {
            if(commentDto.getId().equals(postId)) {
                model.addAttribute("bookComment", CommentDto.builder().build());
            }
        }
        return "book";
    }

    @PostMapping("/main/book/{postId}")
    public String addBookCommentToPage(Model model, @ModelAttribute("bookComment") @Valid CommentDto bookComment, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            bookCommentService.add(bookComment);
        }
        getAllBookComments(model);
        return "book";
    }

    @PutMapping("/main/book/{postId}/{commentId}")
    public String editBookComment(Model model, @PathVariable Long postId, @PathVariable Long commentId, @Valid CommentDto bookComment, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            bookCommentService.update(bookComment);
        }
        getAllBookComments(model);
        return "book";
    }

    @DeleteMapping("/main/book/{postId}")
    public String deleteBookComment(){
        return "book";
    }

    private void getAllBookComments(Model model) {
        model.addAttribute("bookComments", bookCommentService.list());
    }

}
