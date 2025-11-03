package com.voltlab.app_backend.comment.repository;

import com.voltlab.app_backend.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProduct_IdOrderByCreatedAtDesc(Long productId);
    List<Comment> findByUserIdOrderByCreatedAtDesc(Long userId);
    void deleteByIdAndUserId(Long id, Long userId);
}

