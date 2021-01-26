package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class SculptureController {

    @GetMapping("/main/sculpture")
    public String sculpturePage() {
        return "sculpture-category";
    }

    //  wyświetlanie wszystkich elementów kategorii
}
