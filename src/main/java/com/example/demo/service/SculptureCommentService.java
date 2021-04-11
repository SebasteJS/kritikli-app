package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.model.Comment;
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

    public void delete(Long commentId) {
        sculptureCommentRepository.deleteById(commentId);
    }
}
