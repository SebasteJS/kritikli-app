package com.example.demo.service;

import com.example.demo.dto.MusicDto;
import com.example.demo.model.Music;
import com.example.demo.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;

    public Long add(MusicDto musicDto) {
        Music addedMusic = Music.builder()
                .author(musicDto.getAuthor())
                .title(musicDto.getTitle())
                .description(musicDto.getDescription())
                .criticalDescription(musicDto.getCriticalDescription())
                .build();
        return musicRepository.save(addedMusic).getId();
    }

    public List<MusicDto> findAll() {
        List<MusicDto> musicDtoList = new ArrayList<>();
        Iterable<Music> musics = musicRepository.findAll();
        for (Music musicFind : musics) {
            musicDtoList.add(
                    MusicDto.builder()
                            .author(musicFind.getAuthor())
                            .title(musicFind.getTitle())
                            .description(musicFind.getDescription())
                            .criticalDescription(musicFind.getCriticalDescription())
                            .build());
        }
        return musicDtoList;
    }

    public void update(MusicDto musicDto) {
        Optional<Music> editedMusic = musicRepository.findById(musicDto.getId());
        if (editedMusic.isPresent()) {
            Music music = editedMusic.get();
            music.setAuthor(musicDto.getAuthor());
            music.setTitle(musicDto.getTitle());
            music.setDescription(musicDto.getDescription());
            music.setCriticalDescription(musicDto.getCriticalDescription());
            musicRepository.save(music);
        }
    }

    public void delete(Long userId) {
        musicRepository.deleteById(userId);
    }
}
