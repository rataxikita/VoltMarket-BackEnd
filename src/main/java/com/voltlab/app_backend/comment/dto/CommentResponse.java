package com.voltlab.app_backend.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private Long userId;
    private Long productId;
    private String contenido;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Información del usuario que comentó
    private UserInfo user;
    
    @Data
    @AllArgsConstructor
    public static class UserInfo {
        private Long id;
        private String nombre;
        private String apellido;
        private String email;
    }
}

