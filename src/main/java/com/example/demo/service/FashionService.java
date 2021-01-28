package com.example.demo.service;

import com.example.demo.dto.FashionDto;
import com.example.demo.model.Fashion;
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

    public Long add(FashionDto fashionDto) {
        Fashion addedFashion = Fashion.builder()
                .author(fashionDto.getAuthor())
                .title(fashionDto.getTitle())
                .description(fashionDto.getDescription())
                .criticalDescription(fashionDto.getCriticalDescription())
                .build();
        return fashionRepository.save(addedFashion).getId();
    }

    public List<FashionDto> list() {
        List<FashionDto> fashionDtoList = new ArrayList<>();
        Iterable<Fashion> fashions = fashionRepository.findAll();
        for (Fashion fashionFind : fashions) {
            fashionDtoList.add(
                    FashionDto.builder()
                            .author(fashionFind.getAuthor())
                            .title(fashionFind.getTitle())
                            .description(fashionFind.getDescription())
                            .criticalDescription(fashionFind.getCriticalDescription())
                            .build());
        }
        return fashionDtoList;
    }

    public void update(FashionDto fashionDto) {
        Optional<Fashion> editedFashion = fashionRepository.findById(fashionDto.getId());
        if (editedFashion.isPresent()) {
            Fashion fashion = editedFashion.get();
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
