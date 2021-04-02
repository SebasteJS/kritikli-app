package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.service.BookCommentService;
import com.example.demo.service.ImageCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ImageCommentController {

    private final ImageCommentService imageCommentService;

    @GetMapping("/main/book/{id}")
    public String bookCommentsPage(Model model, @PathVariable String id) {
        getAllBookComments(model);
        model.addAttribute("bookComment", CommentDto.builder().build());
        return "book";
    }

    @PostMapping("/main/book/{id}")
    public String addBookCommentToPage(Model model, @ModelAttribute("bookComment") @Valid CommentDto bookComment, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            imageCommentService.add(bookComment);
        }
        getAllBookComments(model);
        return "book";
    }

    private void getAllBookComments(Model model) {
        model.addAttribute("bookComments", imageCommentService.list());
    }
}
