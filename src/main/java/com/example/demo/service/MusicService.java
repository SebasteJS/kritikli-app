package com.example.demo.service;

import com.example.demo.dto.PostDto;
import com.example.demo.model.Post;
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

    public Long add(PostDto postDto) {
        Post addedPost = Post.builder()
                .author(postDto.getAuthor())
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .criticalDescription(postDto.getCriticalDescription())
                .build();
        musicRepository.save(addedPost);
        return addedPost.getId();
    }

    public List<PostDto> list() {
        List<PostDto> postDtoList = new ArrayList<>();
        Iterable<Post> books = musicRepository.findAll();
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
        Optional<Post> editedBook = musicRepository.findById(postDto.getId());
        if (editedBook.isPresent()) {
            Post post = editedBook.get();
            post.setAuthor(postDto.getAuthor());
            post.setTitle(postDto.getTitle());
            post.setDescription(postDto.getDescription());
            post.setCriticalDescription(postDto.getCriticalDescription());
            musicRepository.save(post);
        }
    }

    public PostDto getPostById(Long id) {
        PostDto post = new PostDto();
        List<PostDto> postDtoList = list();
        for(PostDto postDto : postDtoList){
            if(postDto.getId().equals(id)){
                post = PostDto.builder()
                        .author(postDto.getAuthor())
                        .title(postDto.getTitle())
                        .description(postDto.getDescription())
                        .criticalDescription(postDto.getCriticalDescription())
                        .build();
            }
        }
        return post;
    }

    public void delete(Long postId) {
        musicRepository.deleteById(postId);
    }
}
