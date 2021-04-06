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
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FashionCommentController {

    private final FashionCommentService fashionCommentService;

    @GetMapping("/main/fashion/{postId}")
    public String fashionCommentsPage(Model model, @PathVariable Long postId) {
        List<CommentDto> commentDtoList = fashionCommentService.list();
        for (CommentDto commentDto : commentDtoList) {
            if(commentDto.getId().equals(postId)) {
                model.addAttribute("fashionComment", CommentDto.builder().build());
            }
        }
        return "fashion";
    }

    @PostMapping("/main/fashion/{id}")
    public String addFashionCommentToPage(Model model, @ModelAttribute("fashionComment") @Valid CommentDto fashionComment, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            fashionCommentService.add(fashionComment);
        }
        getAllFashionComments(model);
        return "book";
    }

    private void getAllFashionComments(Model model) {
        model.addAttribute("fashionComments", fashionCommentService.list());
    }
}
