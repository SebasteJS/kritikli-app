package com.example.demo.controller;

import com.example.demo.dto.MusicDto;
import com.example.demo.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MusicController {

    private MusicService musicService;

    @GetMapping("/main/music")
    public String musicPage(Model model) {
        getAllMusics(model);
        model.addAttribute("music", MusicDto.builder().build());
        return "music-category";
    }

    private void getAllMusics(Model model) {
        model.addAttribute("musics",musicService.list());
    }

    //  wyświetlanie wszystkich elementów kategorii
}
