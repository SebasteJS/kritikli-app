package com.example.demo.controller;

import com.example.demo.dto.PoetryDto;
import com.example.demo.service.PoetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PoetryController {

    private final PoetryService poetryService;

    @GetMapping("/main/poetry")
    public String poetryPage(Model model) {
        getAllPotries(model);
        model.addAttribute("poetry", PoetryDto.builder().build());
        return "poetry-category";
    }

    private void getAllPotries(Model model) {
        model.addAttribute("poetries",poetryService.list());
    }

    //  wyświetlanie wszystkich elementów kategorii
}
