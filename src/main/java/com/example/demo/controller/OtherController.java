package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.service.OtherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class OtherController {

    private final OtherService otherService;

    @GetMapping("/main/other")
    public String otherPage(Model model) {
        getAllOthers(model);
        model.addAttribute("other", PostDto.builder().build());
        return "other-category";
    }

    @PostMapping("main/other")
    public String addOtherToPage(Model model, @ModelAttribute("other") @Valid PostDto other, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            otherService.add(other);
        }
        getAllOthers(model);
        return "other-category";
    }

    @PutMapping("main/other/{postId}")
    public String editOther(Model model, @PathVariable Long postId, @Valid PostDto postDto) {
        if(otherService.getPostById(postId).getId().equals(postId)) {
            otherService.update(postDto);
        }
        getAllOthers(model);
        return "other-category";
    }

    @DeleteMapping("main/other/{postId}")
    public String deleteOther(Model model, @PathVariable Long postId, @Valid PostDto postDto) {
        if(otherService.getPostById(postId).getId().equals(postId)) {
            otherService.delete(postId);
        }
        getAllOthers(model);
        return "other-category";
    }

    private void getAllOthers(Model model) {
        model.addAttribute("others",otherService.list());
    }

}
