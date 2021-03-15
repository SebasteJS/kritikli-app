package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.PostDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.repository.BookCommentRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.SculptureCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SculptureCommentService {
    private final SculptureCommentRepository sculptureCommentRepository;

    public Long add(CommentDto commentDto) {
        Comment addedComment = Comment.builder()
                .commentText(commentDto.getCommentText())
                .build();
        sculptureCommentRepository.save(addedComment);
        return addedComment.getId();
    }

    public List<CommentDto> list() {
        List<CommentDto> commentDtoList = new ArrayList<>();
        Iterable<Comment> sculptureComments = sculptureCommentRepository.findAll();
        for (Comment commentFind : sculptureComments) {
            commentDtoList.add(
                    CommentDto.builder()
                            .commentText(commentFind.getCommentText())
                            .build());
        }
        return commentDtoList;
    }


    public void update(CommentDto commentDto) {
        Optional<Comment> editedSculptureComment = sculptureCommentRepository.findById(commentDto.getId());
        if (editedSculptureComment.isPresent()) {
            Comment comment = editedSculptureComment.get();
            comment.setCommentText(commentDto.getCommentText());
            sculptureCommentRepository.save(comment);
        }
    }

    public void delete(Long userId) {
        sculptureCommentRepository.deleteById(userId);
    }
}
