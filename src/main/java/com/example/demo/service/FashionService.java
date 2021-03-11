package com.example.demo.service;

import com.example.demo.dto.PostDto;
import com.example.demo.model.Post;
import com.example.demo.repository.FashionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FashionService {
    private final FashionRepository fashionRepository;

    public Long add(PostDto fashionDto) {
        Post addedFashion = Post.builder()
                .author(fashionDto.getAuthor())
                .title(fashionDto.getTitle())
                .description(fashionDto.getDescription())
                .criticalDescription(fashionDto.getCriticalDescription())
                .build();
        fashionRepository.save(addedFashion);
        return addedFashion.getId();
    }

    public List<PostDto> list() {
        List<PostDto> fashionDtoList = new ArrayList<>();
        Iterable<Post> fashions = fashionRepository.findAll();
        for (Post fashionFind : fashions) {
            fashionDtoList.add(
                    PostDto.builder()
                            .author(fashionFind.getAuthor())
                            .title(fashionFind.getTitle())
                            .description(fashionFind.getDescription())
                            .criticalDescription(fashionFind.getCriticalDescription())
                            .build());
        }
        return fashionDtoList;
    }

    public void update(PostDto fashionDto) {
        Optional<Post> editedFashion = fashionRepository.findById(fashionDto.getId());
        if (editedFashion.isPresent()) {
            Post fashion = editedFashion.get();
            fashion.setAuthor(fashionDto.getAuthor());
            fashion.setTitle(fashionDto.getTitle());
            fashion.setDescription(fashionDto.getDescription());
            fashion.setCriticalDescription(fashionDto.getCriticalDescription());
            fashionRepository.save(fashion);
        }
    }

    public void delete(Long userId) {
        fashionRepository.deleteById(userId);
    }
}
