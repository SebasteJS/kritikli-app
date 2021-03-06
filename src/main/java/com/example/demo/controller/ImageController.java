package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/main/image")
    public String imagePage(Model model) {
        getAllImages(model);
        model.addAttribute("image", PostDto.builder().build());
        return "image-category";
    }

    @PostMapping("main/image")
    public String addImageToPage(Model model, @ModelAttribute("image") @Valid PostDto image, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            imageService.add(image);
        }
        getAllImages(model);
        return "image-category";
    }

    @PutMapping("main/image/{postId}")
    public String editImage(Model model, @PathVariable Long postId, @Valid PostDto postDto) {
        if(imageService.getPostById(postId).getId().equals(postId)) {
            imageService.update(postDto);
        }
        getAllImages(model);
        return "image-category";
    }

    @DeleteMapping("main/image/{postId}")
    public String deleteImage(Model model, @PathVariable Long postId, @Valid PostDto postDto) {
        if(imageService.getPostById(postId).getId().equals(postId)) {
            imageService.delete(postId);
        }
        getAllImages(model);
        return "image-category";
    }

    private void getAllImages(Model model) {
        model.addAttribute("images",imageService.list());
    }

}
