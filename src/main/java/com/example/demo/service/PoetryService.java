package com.example.demo.service;

import com.example.demo.dto.PoetryDto;
import com.example.demo.model.Poetry;
import com.example.demo.repository.PoetryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PoetryService {
    private final PoetryRepository poetryRepository;

    public Long add(PoetryDto poetryDto) {
        Poetry addedPoetry = Poetry.builder()
                .author(poetryDto.getAuthor())
                .title(poetryDto.getTitle())
                .description(poetryDto.getDescription())
                .criticalDescription(poetryDto.getCriticalDescription())
                .build();
        return poetryRepository.save(addedPoetry).getId();
    }

    public List<PoetryDto> list() {
        List<PoetryDto> poetryDtoList = new ArrayList<>();
        Iterable<Poetry> poetries = poetryRepository.findAll();
        for (Poetry poetryFind : poetries) {
            poetryDtoList.add(
                    PoetryDto.builder()
                            .author(poetryFind.getAuthor())
                            .title(poetryFind.getTitle())
                            .description(poetryFind.getDescription())
                            .criticalDescription(poetryFind.getCriticalDescription())
                            .build());
        }
        return poetryDtoList;
    }

    public void update(PoetryDto poetryDto) {
        Optional<Poetry> editedPoetry = poetryRepository.findById(poetryDto.getId());
        if (editedPoetry.isPresent()) {
            Poetry poetry = editedPoetry.get();
            poetry.setAuthor(poetryDto.getAuthor());
            poetry.setTitle(poetryDto.getTitle());
            poetry.setDescription(poetryDto.getDescription());
            poetry.setCriticalDescription(poetryDto.getCriticalDescription());
            poetryRepository.save(poetry);
        }
    }

    public void delete(Long userId) {
        poetryRepository.deleteById(userId);
    }
}
