package com.example.demo.repository;

import com.example.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoetryCommentRepository extends JpaRepository<Comment, Long> {
}
