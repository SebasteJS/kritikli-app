package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/main/book")
    public String bookPage(Model model) {
        getAllBooks(model);
        model.addAttribute("book", BookDto.builder().build());
        return "book-category";
    }

    private void getAllBooks(Model model) {
        model.addAttribute("books",bookService.list());
    }

//  wyświetlanie wszystkich elementów kategorii
//
}
