package com.voltlab.app_backend.comment.controller;

import com.voltlab.app_backend.comment.dto.CommentResponse;
import com.voltlab.app_backend.comment.dto.CreateCommentRequest;
import com.voltlab.app_backend.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Obtener comentarios de un producto
     * GET /api/comments/product/{productId}
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<CommentResponse>> getProductComments(@PathVariable Long productId) {
        return ResponseEntity.ok(commentService.getProductComments(productId));
    }

    /**
     * Crear comentario
     * POST /api/comments
     */
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @Valid @RequestBody CreateCommentRequest request,
            HttpServletRequest httpRequest
    ) {
        // Obtener userId del token JWT (guardado por el interceptor)
        Long userId = (Long) httpRequest.getAttribute("userId");
        
        if (userId == null) {
            throw new RuntimeException("Usuario no autenticado");
        }
        
        CommentResponse response = commentService.createComment(userId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Eliminar comentario
     * DELETE /api/comments/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteComment(
            @PathVariable Long id,
            HttpServletRequest httpRequest
    ) {
        Long userId = (Long) httpRequest.getAttribute("userId");
        
        if (userId == null) {
            throw new RuntimeException("Usuario no autenticado");
        }
        
        commentService.deleteComment(id, userId);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Comentario eliminado exitosamente");
        return ResponseEntity.ok(response);
    }
}

