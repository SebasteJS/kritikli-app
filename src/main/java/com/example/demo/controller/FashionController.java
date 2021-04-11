package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.service.FashionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class FashionController {

    private final FashionService fashionService;

    @GetMapping("/main/fashion")
    public String fashionPage(Model model) {
        getAllFashions(model);
        model.addAttribute("fashion", PostDto.builder().build());
        return "fashion-category";
    }

    @PostMapping("main/fashion")
    public String addFashionToPage(Model model, @ModelAttribute("fashion") @Valid PostDto fashion, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            fashionService.add(fashion);
        }
        getAllFashions(model);
        return "fashion-category";
    }

    @PutMapping("main/fashion/{postId}")
    public String editFashion(Model model, @PathVariable Long postId, @Valid PostDto postDto) {
        if(fashionService.getPostById(postId).getId().equals(postId)) {
            fashionService.update(postDto);
        }
        getAllFashions(model);
        return "fashion-category";
    }

    @DeleteMapping("main/fashion/{postId}")
    public String deleteFashion(Model model,@PathVariable Long postId, @Valid PostDto postDto) {
        if(fashionService.getPostById(postId).getId().equals(postId)) {
            fashionService.delete(postId);
        }
        getAllFashions(model);
        return "fashion-category";
    }

    private void getAllFashions(Model model) {
        model.addAttribute("fashions",fashionService.list());
    }
}
