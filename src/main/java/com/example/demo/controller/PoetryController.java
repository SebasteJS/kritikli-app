package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.service.PoetryService;
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
public class PoetryController {

    private final PoetryService poetryService;

    @GetMapping("/main/poetry")
    public String poetryPage(Model model) {
        getAllPotries(model);
        model.addAttribute("poetry", PostDto.builder().build());
        return "poetry-category";
    }

    @PostMapping("main/poetry")
    public String addPoetryToPage(Model model, @ModelAttribute("poetry") @Valid PostDto poetry, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            poetryService.add(poetry);
        }
        getAllPotries(model);
        return "poetry-category";
    }

    private void getAllPotries(Model model) {
        model.addAttribute("poetries",poetryService.list());
    }

    //  wyświetlanie wszystkich elementów kategorii
}
