package com.example.demo.controller;

import com.example.demo.service.BookService;
import com.example.demo.service.OtherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OtherController {

    @Autowired
    private OtherService otherService;

    @GetMapping("/main/other")
    public String otherPage(Model model) {
        model.addAttribute("other",otherService.findAll());
        model.addAttribute("message", "My message to working controller");
        return "other-category";
    }
    //  wyświetlanie wszystkich elementów kategorii
}
