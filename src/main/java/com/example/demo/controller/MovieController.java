package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/main/movie")
    public String moviePage(Model model) {
        getAllMovies(model);
        model.addAttribute("movie", PostDto.builder().build());
        return "movie-category";
    }

    @PostMapping("main/movie")
    public String addMovieToPage(Model model, @ModelAttribute("movie") @Valid PostDto movie, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            movieService.add(movie);
        }
        getAllMovies(model);
        return "movie-category";
    }

    @PutMapping("main/movie/{postId}")
    public String editMovie(Model model, @PathVariable Long postId, @Valid PostDto postDto) {
        if (movieService.getPostById(postId).getId().equals(postId)) {
            movieService.update(postDto);
        }
        getAllMovies(model);
        return "movie-category";
    }

    @DeleteMapping("main/movie/{postId}")
    public String deleteMovie(Model model, @PathVariable Long postId, @Valid PostDto postDto) {
        if (movieService.getPostById(postId).getId().equals(postId)) {
            movieService.delete(postId);
        }
        getAllMovies(model);
        return "movie-category";
    }

    private void getAllMovies(Model model) {
        model.addAttribute("movies", movieService.list());
    }

}
