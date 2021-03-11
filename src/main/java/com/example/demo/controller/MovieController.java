package com.example.demo.controller;

import com.example.demo.service.MovieService;
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
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/main/movie")
    public String moviePage(Model model) {
        getAllMovies(model);
        model.addAttribute("movie", MovieDto.builder().build());
        return "movie-category";
    }

    @PostMapping("main/movie")
    public String addMovieToPage(Model model, @ModelAttribute("movie") @Valid MovieDto movie, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            movieService.add(movie);
        }
        getAllMovies(model);
        return "movie-category";
    }


    private void getAllMovies(Model model) {
        model.addAttribute("movies",movieService.list());
    }

    //  wyświetlanie wszystkich elementów kategorii
}
