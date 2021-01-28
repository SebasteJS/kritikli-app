package com.example.demo.service;

import com.example.demo.dto.OtherDto;
import com.example.demo.model.Other;
import com.example.demo.repository.OtherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OtherService {
    private final OtherRepository otherRepository;

    public Long add(OtherDto otherDto) {
        Other addedOther = Other.builder()
                .author(otherDto.getAuthor())
                .title(otherDto.getTitle())
                .description(otherDto.getDescription())
                .criticalDescription(otherDto.getCriticalDescription())
                .build();
        return otherRepository.save(addedOther).getId();
    }

    public List<OtherDto> list() {
        List<OtherDto> otherDtoList = new ArrayList<>();
        Iterable<Other> others = otherRepository.findAll();
        for (Other otherFind : others) {
            otherDtoList.add(
                    OtherDto.builder()
                            .author(otherFind.getAuthor())
                            .title(otherFind.getTitle())
                            .description(otherFind.getDescription())
                            .criticalDescription(otherFind.getCriticalDescription())
                            .build());
        }
        return otherDtoList;
    }

    public void update(OtherDto otherDto) {
        Optional<Other> editedOther = otherRepository.findById(otherDto.getId());
        if (editedOther.isPresent()) {
            Other other = editedOther.get();
            other.setAuthor(otherDto.getAuthor());
            other.setTitle(otherDto.getTitle());
            other.setDescription(otherDto.getDescription());
            other.setCriticalDescription(otherDto.getCriticalDescription());
            otherRepository.save(other);
        }
    }

    public void delete(Long userId) {
        otherRepository.deleteById(userId);
    }
}
