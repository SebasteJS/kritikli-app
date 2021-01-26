package com.example.demo.service;

import com.example.demo.dto.ImageDto;
import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Long add(ImageDto imageDto) {
        Image addedImage = Image.builder()
                .author(imageDto.getAuthor())
                .title(imageDto.getTitle())
                .description(imageDto.getDescription())
                .criticalDescription(imageDto.getCriticalDescription())
                .build();
        return imageRepository.save(addedImage).getId();
    }

    public List<ImageDto> findAll() {
        List<ImageDto> imageDtoList = new ArrayList<>();
        Iterable<Image> images = imageRepository.findAll();
        for (Image imageFind : images) {
            imageDtoList.add(
                    ImageDto.builder()
                            .author(imageFind.getAuthor())
                            .title(imageFind.getTitle())
                            .description(imageFind.getDescription())
                            .criticalDescription(imageFind.getCriticalDescription())
                            .build());
        }
        return imageDtoList;
    }

    public void update(ImageDto imageDto) {
        Optional<Image> editedImage = imageRepository.findById(imageDto.getId());
        if (editedImage.isPresent()) {
            Image image = editedImage.get();
            image.setAuthor(imageDto.getAuthor());
            image.setTitle(imageDto.getTitle());
            image.setDescription(imageDto.getDescription());
            image.setCriticalDescription(imageDto.getCriticalDescription());
            imageRepository.save(image);
        }
    }

    public void delete(Long userId) {
        imageRepository.deleteById(userId);
    }
}
