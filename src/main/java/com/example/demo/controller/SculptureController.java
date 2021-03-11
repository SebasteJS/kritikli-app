package com.example.demo.controller;

import com.example.demo.service.SculptureService;
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
public class SculptureController {

    private final SculptureService sculptureService;

    @GetMapping("/main/sculpture")
    public String sculpturePage(Model model) {
        getAllSculptures(model);
        model.addAttribute("sculpture", SculptureDto.builder().build());
        model.addAttribute("message", "My message to working controller");
        return "sculpture-category";
    }

    @PostMapping("main/sculpture")
    public String addSculptureToPage(Model model, @ModelAttribute("sculpture") @Valid SculptureDto sculpture, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            sculptureService.add(sculpture);
        }
        getAllSculptures(model);
        return "sculpture-category";
    }

    private void getAllSculptures(Model model) {
        model.addAttribute("sculptures",sculptureService.list());
    }

}
