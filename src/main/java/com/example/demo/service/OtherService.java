package com.example.demo.service;

import com.example.demo.dto.PostDto;
import com.example.demo.model.Post;
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

    public Long add(PostDto postDto) {
        Post addedPost = Post.builder()
                .author(postDto.getAuthor())
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .criticalDescription(postDto.getCriticalDescription())
                .build();
        otherRepository.save(addedPost);
        return addedPost.getId();
    }

    public List<PostDto> list() {
        List<PostDto> postDtoList = new ArrayList<>();
        Iterable<Post> books = otherRepository.findAll();
        for (Post postFind : books) {
            postDtoList.add(
                    PostDto.builder()
                            .author(postFind.getAuthor())
                            .title(postFind.getTitle())
                            .description(postFind.getDescription())
                            .criticalDescription(postFind.getCriticalDescription())
                            .build());
        }
        return postDtoList;
    }


    public void update(PostDto postDto) {
        Optional<Post> editedBook = otherRepository.findById(postDto.getId());
        if (editedBook.isPresent()) {
            Post post = editedBook.get();
            post.setAuthor(postDto.getAuthor());
            post.setTitle(postDto.getTitle());
            post.setDescription(postDto.getDescription());
            post.setCriticalDescription(postDto.getCriticalDescription());
            otherRepository.save(post);
        }
    }

    public void delete(Long userId) {
        otherRepository.deleteById(userId);
    }
}
