package com.example.demo.controller;

import com.example.demo.dto.MovieDto;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private MovieService movieService;

    @GetMapping("/main/movie")
    public String moviePage(Model model) {
        getAllMovies(model);
        model.addAttribute("movie", MovieDto.builder().build());
        return "movie-category";
    }


    private void getAllMovies(Model model) {
        model.addAttribute("movies",movieService.list());
    }

    //  wyświetlanie wszystkich elementów kategorii
}
