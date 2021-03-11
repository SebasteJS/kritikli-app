package com.example.demo.controller;

import com.example.demo.service.MusicService;
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
public class MusicController {

    private final MusicService musicService;

    @GetMapping("/main/music")
    public String musicPage(Model model) {
        getAllMusics(model);
        model.addAttribute("music", MusicDto.builder().build());
        return "music-category";
    }

    @PostMapping("main/music")
    public String addMusicToPage(Model model, @ModelAttribute("music") @Valid MusicDto music, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            musicService.add(music);
        }
        getAllMusics(model);
        return "music-category";
    }

    private void getAllMusics(Model model) {
        model.addAttribute("musics",musicService.list());
    }

    //  wyświetlanie wszystkich elementów kategorii
}
