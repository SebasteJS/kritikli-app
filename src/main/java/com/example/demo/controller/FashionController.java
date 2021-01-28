package com.example.demo.controller;

import com.example.demo.dto.FashionDto;
import com.example.demo.service.FashionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FashionController {

    private FashionService fashionService;

    @GetMapping("/main/fashion")
    public String fashionPage(Model model) {
        getAllFashions(model);
        model.addAttribute("fashion", FashionDto.builder().build());
        return "fashion-category";
    }

    private void getAllFashions(Model model) {
        model.addAttribute("fashions",fashionService.list());
    }
    //  wyświetlanie wszystkich elementów kategorii
}
