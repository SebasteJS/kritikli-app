package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/main/book")
    public String bookPage(Model model) {
        getAllBooks(model);
        model.addAttribute("book", PostDto.builder().build());
        return "book-category";
    }

    @PostMapping("main/book")
    public String addBookToPage(Model model, @ModelAttribute("book") @Valid PostDto book, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            bookService.add(book);
        }
        getAllBooks(model);
        return "book-category";
    }

    private void getAllBooks(Model model) {
        model.addAttribute("books",bookService.list());
    }

}
