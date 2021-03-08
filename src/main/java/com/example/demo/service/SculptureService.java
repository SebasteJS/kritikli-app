package com.example.demo.service;

import com.example.demo.dto.SculptureDto;
import com.example.demo.model.Sculpture;
import com.example.demo.repository.SculptureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SculptureService {
    private final SculptureRepository sculptureRepository;

    public Long add(SculptureDto sculptureDto) {
        Sculpture addedSculpture = Sculpture.builder()
                .author(sculptureDto.getAuthor())
                .title(sculptureDto.getTitle())
                .description(sculptureDto.getDescription())
                .criticalDescription(sculptureDto.getCriticalDescription())
                .build();
        sculptureRepository.save(addedSculpture);
        return addedSculpture.getId();    }

    public List<SculptureDto> list() {
        List<SculptureDto> sculptureDtoList = new ArrayList<>();
        Iterable<Sculpture> musics = sculptureRepository.findAll();
        for (Sculpture musicFind : musics) {
            sculptureDtoList.add(
                    SculptureDto.builder()
                            .author(musicFind.getAuthor())
                            .title(musicFind.getTitle())
                            .description(musicFind.getDescription())
                            .criticalDescription(musicFind.getCriticalDescription())
                            .build());
        }
        return sculptureDtoList;
    }

    public void update(SculptureDto sculptureDto) {
        Optional<Sculpture> editedSculpture = sculptureRepository.findById(sculptureDto.getId());
        if (editedSculpture.isPresent()) {
            Sculpture sculpture = editedSculpture.get();
            sculpture.setAuthor(sculptureDto.getAuthor());
            sculpture.setTitle(sculptureDto.getTitle());
            sculpture.setDescription(sculptureDto.getDescription());
            sculpture.setCriticalDescription(sculptureDto.getCriticalDescription());
            sculptureRepository.save(sculpture);
        }
    }

    public void delete(Long userId) {
        sculptureRepository.deleteById(userId);
    }
}
