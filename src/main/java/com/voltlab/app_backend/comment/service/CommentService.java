package com.voltlab.app_backend.comment.service;

import com.voltlab.app_backend.comment.dto.CommentResponse;
import com.voltlab.app_backend.comment.dto.CreateCommentRequest;
import com.voltlab.app_backend.comment.model.Comment;
import com.voltlab.app_backend.comment.repository.CommentRepository;
import com.voltlab.app_backend.product.model.Product;
import com.voltlab.app_backend.product.repository.ProductRepository;
import com.voltlab.app_backend.model.User;
import com.voltlab.app_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<CommentResponse> getProductComments(Long productId) {
        return commentRepository.findByProduct_IdOrderByCreatedAtDesc(productId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public CommentResponse createComment(Long userId, CreateCommentRequest request) {
        // Verificar que el producto existe
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Crear comentario
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setProduct(product);
        comment.setContenido(request.getContenido());

        Comment saved = commentRepository.save(comment);
        return toResponse(saved);
    }

    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        
        // Verificar que el usuario es el dueño del comentario
        if (!comment.getUserId().equals(userId)) {
            throw new RuntimeException("No tienes permiso para eliminar este comentario");
        }
        
        commentRepository.delete(comment);
    }

    private CommentResponse toResponse(Comment comment) {
        // Obtener información del usuario
        User user = userRepository.findById(comment.getUserId()).orElse(null);
        
        CommentResponse.UserInfo userInfo = null;
        if (user != null) {
            userInfo = new CommentResponse.UserInfo(
                    user.getId(),
                    user.getNombre(),
                    user.getApellido(),
                    user.getEmail()
            );
        }

        return new CommentResponse(
                comment.getId(),
                comment.getUserId(),
                comment.getProduct().getId(),
                comment.getContenido(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                userInfo
        );
    }
}

