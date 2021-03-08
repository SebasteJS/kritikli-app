package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.OtherDto;
import com.example.demo.service.OtherService;
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
public class OtherController {

    private final OtherService otherService;

    @GetMapping("/main/other")
    public String otherPage(Model model) {
        getAllOthers(model);
        model.addAttribute("other", OtherDto.builder().build());
        return "other-category";
    }

    @PostMapping("main/other")
    public String addOtherToPage(Model model, @ModelAttribute("other") @Valid OtherDto other, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            otherService.add(other);
        }
        getAllOthers(model);
        return "other-category";
    }

    private void getAllOthers(Model model) {
        model.addAttribute("others",otherService.list());
    }

    //  wyświetlanie wszystkich elementów kategorii
}
