package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.PostDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.repository.BookCommentRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.FashionCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FashionCommentService {
    private final FashionCommentRepository fashionCommentRepository;

    public Long add(CommentDto commentDto) {
        Comment addedComment = Comment.builder()
                .commentText(commentDto.getCommentText())
                .build();
        fashionCommentRepository.save(addedComment);
        return addedComment.getId();
    }

    public List<CommentDto> list() {
        List<CommentDto> commentDtoList = new ArrayList<>();
        Iterable<Comment> fashionComments = fashionCommentRepository.findAll();
        for (Comment commentFind : fashionComments) {
            commentDtoList.add(
                    CommentDto.builder()
                            .commentText(commentFind.getCommentText())
                            .build());
        }
        return commentDtoList;
    }


    public void update(CommentDto commentDto) {
        Optional<Comment> editedFashionComment = fashionCommentRepository.findById(commentDto.getId());
        if (editedFashionComment.isPresent()) {
            Comment comment = editedFashionComment.get();
            comment.setCommentText(commentDto.getCommentText());
            fashionCommentRepository.save(comment);
        }
    }

    public void delete(Long userId) {
        fashionCommentRepository.deleteById(userId);
    }
}
