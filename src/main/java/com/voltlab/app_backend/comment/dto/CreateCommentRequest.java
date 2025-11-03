package com.voltlab.app_backend.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCommentRequest {
    
    @NotNull(message = "El ID del producto es obligatorio")
    private Long productId;
    
    @NotBlank(message = "El contenido del comentario no puede estar vacío")
    private String contenido;
}

