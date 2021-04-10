package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.PostDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.repository.BookCommentRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MovieCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieCommentService {
    private final MovieCommentRepository movieCommentRepository;

    public Long add(CommentDto commentDto) {
        Comment addedComment = Comment.builder()
                .commentText(commentDto.getCommentText())
                .build();
        movieCommentRepository.save(addedComment);
        return addedComment.getId();
    }

    public List<CommentDto> list() {
        List<CommentDto> commentDtoList = new ArrayList<>();
        Iterable<Comment> movieComments = movieCommentRepository.findAll();
        for (Comment commentFind : movieComments) {
            commentDtoList.add(
                    CommentDto.builder()
                            .commentText(commentFind.getCommentText())
                            .build());
        }
        return commentDtoList;
    }


    public void update(CommentDto commentDto) {
        Optional<Comment> editedMovieComment = movieCommentRepository.findById(commentDto.getId());
        if (editedMovieComment.isPresent()) {
            Comment comment = editedMovieComment.get();
            comment.setCommentText(commentDto.getCommentText());
            movieCommentRepository.save(comment);
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
        movieCommentRepository.deleteById(userId);
    }
}
