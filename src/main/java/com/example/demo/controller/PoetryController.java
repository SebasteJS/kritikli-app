package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class PoetryController {

    @GetMapping("/main/poetry")
    public String poetryPage() {
        return "poetry-category";
    }

    //  wyświetlanie wszystkich elementów kategorii
}
