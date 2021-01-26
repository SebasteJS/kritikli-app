package com.example.demo.service;

import com.example.demo.dto.MovieDto;
import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Long add(MovieDto movieDto) {
        Movie addedMovie = Movie.builder()
                .author(movieDto.getAuthor())
                .title(movieDto.getTitle())
                .description(movieDto.getDescription())
                .criticalDescription(movieDto.getCriticalDescription())
                .build();
        return movieRepository.save(addedMovie).getId();
    }

    public List<MovieDto> findAll() {
        List<MovieDto> movieDtoList = new ArrayList<>();
        Iterable<Movie> movies = movieRepository.findAll();
        for (Movie movieFind : movies) {
            movieDtoList.add(
                    MovieDto.builder()
                            .author(movieFind.getAuthor())
                            .title(movieFind.getTitle())
                            .description(movieFind.getDescription())
                            .criticalDescription(movieFind.getCriticalDescription())
                            .build());
        }
        return movieDtoList;
    }

    public void update(MovieDto movieDto) {
        Optional<Movie> editedMovie = movieRepository.findById(movieDto.getId());
        if (editedMovie.isPresent()) {
            Movie movie = editedMovie.get();
            movie.setAuthor(movieDto.getAuthor());
            movie.setTitle(movieDto.getTitle());
            movie.setDescription(movieDto.getDescription());
            movie.setCriticalDescription(movieDto.getCriticalDescription());
            movieRepository.save(movie);
        }
    }

    public void delete(Long userId) {
        movieRepository.deleteById(userId);
    }
}
