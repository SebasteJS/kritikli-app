package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class PostController {

    @GetMapping("/main/currentCategory/add-post")
    public String addPostPage() {
        return "/fragments/add-post";
    }
    //wysyłanie posta do serwisu a potem do bazy danych
}
