package com.example.demo.controller;

import com.example.demo.dto.OtherDto;
import com.example.demo.service.OtherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    private void getAllOthers(Model model) {
        model.addAttribute("others",otherService.list());
    }

    //  wyświetlanie wszystkich elementów kategorii
}
