package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.service.SculptureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SculptureController {

    private final SculptureService sculptureService;

    @GetMapping("/main/sculpture")
    public String sculpturePage(Model model) {
        getAllSculptures(model);
        model.addAttribute("sculpture", PostDto.builder().build());
        return "sculpture-category";
    }

    @PostMapping("main/sculpture")
    public String addSculptureToPage(Model model, @ModelAttribute("sculpture") @Valid PostDto sculpture, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            sculptureService.add(sculpture);
        }
        getAllSculptures(model);
        return "sculpture-category";
    }

    @PutMapping("main/sculpture/{postId}")
    public String editSculpture(@PathVariable Long postId, @Valid PostDto postDto) {
        if(postDto.getId().equals(postId)) {
            sculptureService.update(postDto);
        }
        return "sculpture-category";
    }

    @DeleteMapping("main/sculpture/{postId}")
    public String deleteSculpture(@PathVariable Long postId, @Valid PostDto postDto) {
        if(postDto.getId().equals(postId)) {
            sculptureService.delete(postId);
        }
        return "sculpture-category";
    }

    private void getAllSculptures(Model model) {
        model.addAttribute("sculptures",sculptureService.list());
    }

}
