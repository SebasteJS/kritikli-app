package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.PostDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.repository.BookCommentRepository;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieCommentService {
    private final BookCommentRepository bookCommentRepository;

    public Long add(CommentDto commentDto) {
        Comment addedComment = Comment.builder()
                .commentText(commentDto.getCommentText())
                .build();
        bookCommentRepository.save(addedComment);
        return addedComment.getId();
    }

    public List<CommentDto> list() {
        List<CommentDto> commentDtoList = new ArrayList<>();
        Iterable<Comment> bookComments = bookCommentRepository.findAll();
        for (Comment commentFind : bookComments) {
            commentDtoList.add(
                    CommentDto.builder()
                            .commentText(commentFind.getCommentText())
                            .build());
        }
        return commentDtoList;
    }


    public void update(CommentDto commentDto) {
        Optional<Comment> editedBookComment = bookCommentRepository.findById(commentDto.getId());
        if (editedBookComment.isPresent()) {
            Comment comment = editedBookComment.get();
            comment.setCommentText(commentDto.getCommentText());
            bookCommentRepository.save(comment);
        }
    }

    public void delete(Long userId) {
        bookCommentRepository.deleteById(userId);
    }
}
