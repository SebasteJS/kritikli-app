package com.example.demo.controller;

import com.example.demo.dto.SculptureDto;
import com.example.demo.service.SculptureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    private void getAllSculptures(Model model) {
        model.addAttribute("sculptures",sculptureService.list());
    }

}
