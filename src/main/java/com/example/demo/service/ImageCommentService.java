package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.PostDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.repository.BookCommentRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ImageCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageCommentService {
    private final ImageCommentRepository imageCommentRepository;

    public Long add(CommentDto commentDto) {
        Comment addedComment = Comment.builder()
                .commentText(commentDto.getCommentText())
                .build();
        imageCommentRepository.save(addedComment);
        return addedComment.getId();
    }

    public List<CommentDto> list() {
        List<CommentDto> commentDtoList = new ArrayList<>();
        Iterable<Comment> imageComments = imageCommentRepository.findAll();
        for (Comment commentFind : imageComments) {
            commentDtoList.add(
                    CommentDto.builder()
                            .commentText(commentFind.getCommentText())
                            .build());
        }
        return commentDtoList;
    }


    public void update(CommentDto commentDto) {
        Optional<Comment> editedImageComment = imageCommentRepository.findById(commentDto.getId());
        if (editedImageComment.isPresent()) {
            Comment comment = editedImageComment.get();
            comment.setCommentText(commentDto.getCommentText());
            imageCommentRepository.save(comment);
        }
    }

    public void delete(Long userId) {
        imageCommentRepository.deleteById(userId);
    }
}
