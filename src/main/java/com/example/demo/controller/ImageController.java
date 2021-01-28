package com.example.demo.controller;

import com.example.demo.dto.ImageDto;
import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/main/image")
    public String imagePage(Model model) {
        getAllImages(model);
        model.addAttribute("image", ImageDto.builder().build());
        return "image-category";
    }

    private void getAllImages(Model model) {
        model.addAttribute("images",imageService.list());
    }

    //  wyświetlanie wszystkich elementów kategorii
}
