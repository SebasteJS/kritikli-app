package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @GetMapping("/main/music")
    public String musicPage(Model model) {
        getAllMusics(model);
        model.addAttribute("music", PostDto.builder().build());
        return "music-category";
    }

    @PostMapping("main/music")
    public String addMusicToPage(Model model, @ModelAttribute("music") @Valid PostDto music, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            musicService.add(music);
        }
        getAllMusics(model);
        return "music-category";
    }

    @PutMapping("main/music/{postId}")
    public String editMusic(Model model, @PathVariable Long postId, @Valid PostDto postDto) {
        if(musicService.getPostById(postId).getId().equals(postId)) {
            musicService.update(postDto);
        }
        getAllMusics(model);
        return "music-category";
    }

    @DeleteMapping("main/music/{postId}")
    public String deleteMusic(Model model, @PathVariable Long postId, @Valid PostDto postDto) {
        if(musicService.getPostById(postId).getId().equals(postId)) {
            musicService.delete(postId);
        }
        getAllMusics(model);
        return "music-category";
    }

    private void getAllMusics(Model model) {
        model.addAttribute("musics",musicService.list());
    }

}
