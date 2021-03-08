package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.FashionDto;
import com.example.demo.service.FashionService;
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
public class FashionController {

    private final FashionService fashionService;

    @GetMapping("/main/fashion")
    public String fashionPage(Model model) {
        getAllFashions(model);
        model.addAttribute("fashion", FashionDto.builder().build());
        return "fashion-category";
    }

    @PostMapping("main/fashion")
    public String addFashionToPage(Model model, @ModelAttribute("fashion") @Valid FashionDto fashion, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            fashionService.add(fashion);
        }
        getAllFashions(model);
        return "fashion-category";
    }

    private void getAllFashions(Model model) {
        model.addAttribute("fashions",fashionService.list());
    }
    //  wyświetlanie wszystkich elementów kategorii
}
