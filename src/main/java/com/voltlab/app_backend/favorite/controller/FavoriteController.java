package com.voltlab.app_backend.favorite.controller;

import com.voltlab.app_backend.favorite.dto.FavoriteResponse;
import com.voltlab.app_backend.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteResponse>> list(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(favoriteService.list(userId));
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<FavoriteResponse> add(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId
    ) {
        return ResponseEntity.ok(favoriteService.add(userId, productId));
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<Map<String, Object>> remove(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId
    ) {
        favoriteService.remove(userId, productId);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Favorito eliminado");
        return ResponseEntity.ok(body);
    }

    /**
     * Verificar si un producto está en favoritos del usuario
     * GET /api/favorites/{userId}/check/{productId}
     */
    @GetMapping("/{userId}/check/{productId}")
    public ResponseEntity<Map<String, Boolean>> checkFavorite(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId
    ) {
        boolean isFavorite = favoriteService.isFavorite(userId, productId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isFavorite", isFavorite);
        return ResponseEntity.ok(response);
    }

    // Manejo de errores simple
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        Map<String, String> fieldErrors = new HashMap<>();
        for (var error : ex.getBindingResult().getAllErrors()) {
            String fieldName = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        }
        errors.put("message", "Error de validación");
        errors.put("errors", fieldErrors);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeExceptions(RuntimeException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }
}


