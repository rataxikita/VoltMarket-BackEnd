package com.voltlab.app_backend.config;

import com.voltlab.app_backend.auth.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        // Permitir OPTIONS (preflight CORS)
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        String path = request.getRequestURI();
        String method = request.getMethod();
        
        // Rutas públicas (no requieren token)
        if (isPublicPath(path, method)) {
            log.debug("Ruta pública permitida: {} {}", method, path);
            return true;
        }

        // Obtener header Authorization
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("Token no proporcionado para ruta: {} - Method: {}", path, request.getMethod());
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, 
                "Token no proporcionado o formato inválido. Use: Authorization: Bearer <token>");
            return false;
        }

        try {
            // Extraer token (quitando "Bearer ")
            String token = authHeader.substring(7);
            
            // Validar token
            if (!jwtUtil.validateToken(token)) {
                log.warn("Token inválido o expirado para ruta: {}", path);
                sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, 
                    "Token inválido o expirado");
                return false;
            }

            // Guardar datos del usuario en los atributos del request
            Long userId = jwtUtil.extractUserId(token);
            String email = jwtUtil.extractEmail(token);
            String role = jwtUtil.extractRole(token);
            
            request.setAttribute("userId", userId);
            request.setAttribute("email", email);
            request.setAttribute("role", role);
            
            log.debug("Token válido para usuario: {} en ruta: {}", email, path);
            return true;

        } catch (Exception e) {
            log.error("Error al procesar token para ruta: {}", path, e);
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, 
                "Error al procesar token: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Verifica si la ruta es pública (no requiere autenticación)
     * GET de productos y comentarios son públicos, pero POST/PUT/DELETE requieren autenticación
     */
    private boolean isPublicPath(String path, String method) {
        // Rutas de autenticación siempre públicas
        if (path.startsWith("/api/auth/login") || path.startsWith("/api/auth/register")) {
            return true;
        }
        
        // Rutas de reconocimiento públicas
        if (path.contains("/recognition/health") || 
            path.contains("/recognition/categories") || 
            path.contains("/recognition/init-data")) {
            return true;
        }
        
        // GET de productos es público (ver productos, categorías, buscar)
        if (path.startsWith("/api/products") && "GET".equals(method)) {
            return true;
        }
        
        // GET de comentarios es público (ver comentarios de un producto)
        if (path.startsWith("/api/comments") && "GET".equals(method)) {
            return true;
        }
        
        // Servir archivos estáticos (uploads)
        if (path.startsWith("/uploads/")) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Envía una respuesta de error en formato JSON
     */
    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws Exception {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String jsonResponse = String.format("{\"message\":\"%s\",\"status\":%d}", message, status);
        response.getWriter().write(jsonResponse);
    }
}