package com.example.demo.controller;

import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class FashionController {

    @Autowired
    private BookService bookService;

    @GetMapping("/main/book")
    public String bookPage(Model model) {
        model.addAttribute("book",bookService.findAll());
        model.addAttribute("message", "My message to working controller");
        return "book-category";
    }
    //  wyświetlanie wszystkich elementów kategorii
}
