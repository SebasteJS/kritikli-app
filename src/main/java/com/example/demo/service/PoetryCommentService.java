package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.PostDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.repository.BookCommentRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PoetryCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PoetryCommentService {
    private final PoetryCommentRepository poetryCommentRepository;

    public Long add(CommentDto commentDto) {
        Comment addedComment = Comment.builder()
                .commentText(commentDto.getCommentText())
                .build();
        poetryCommentRepository.save(addedComment);
        return addedComment.getId();
    }

    public List<CommentDto> list() {
        List<CommentDto> commentDtoList = new ArrayList<>();
        Iterable<Comment> poetryComments = poetryCommentRepository.findAll();
        for (Comment commentFind : poetryComments) {
            commentDtoList.add(
                    CommentDto.builder()
                            .commentText(commentFind.getCommentText())
                            .build());
        }
        return commentDtoList;
    }


    public void update(CommentDto commentDto) {
        Optional<Comment> editedPoetryComment = poetryCommentRepository.findById(commentDto.getId());
        if (editedPoetryComment.isPresent()) {
            Comment comment = editedPoetryComment.get();
            comment.setCommentText(commentDto.getCommentText());
            poetryCommentRepository.save(comment);
        }
    }

    public List<CommentDto> listWithSpecifiedId(Long postId) {
        List<CommentDto> commentsWithSpecifiedId = new ArrayList<>();
        List<CommentDto> commentDtoList = list();
        for(CommentDto commentDto : commentDtoList) {
            if(commentDto.getPostId().equals(postId)){
                commentsWithSpecifiedId.add(
                        CommentDto.builder()
                                .commentText(commentDto.getCommentText())
                                .build());
            }
        }
        return commentsWithSpecifiedId;
    }

    public CommentDto getCommentById(Long commentId) {
        CommentDto comment = new CommentDto();
        List<CommentDto> commentDtoList = list();
        for(CommentDto commentDto : commentDtoList) {
            if(commentDto.getId().equals(commentId)) {
                comment = CommentDto.builder()
                        .commentText(commentDto.getCommentText())
                        .build();

            }
        }
        return comment;
    }

    public void delete(Long userId) {
        poetryCommentRepository.deleteById(userId);
    }
}
