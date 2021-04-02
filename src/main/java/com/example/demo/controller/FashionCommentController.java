package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.service.BookCommentService;
import com.example.demo.service.FashionCommentService;
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
public class FashionCommentController {

    private final FashionCommentService fashionCommentService;

    @GetMapping("/main/book/{id}")
    public String fashionCommentsPage(Model model, @PathVariable String id) {
        getAllFashionComments(model);
        model.addAttribute("fashionComment", CommentDto.builder().build());
        return "book";
    }

    @PostMapping("/main/book/{id}")
    public String addBookCommentToPage(Model model, @ModelAttribute("fashionComment") @Valid CommentDto fashionComment, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            fashionCommentService.add(fashionComment);
        }
        getAllFashionComments(model);
        return "book";
    }

    private void getAllFashionComments(Model model) {
        model.addAttribute("bookComments", fashionCommentService.list());
    }
}
