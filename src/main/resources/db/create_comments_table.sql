    -- Crear tabla de comentarios
    -- Ejecutar este script en MySQL

    USE voltmarket_db;

    CREATE TABLE IF NOT EXISTS comments (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        user_id BIGINT NOT NULL,
        product_id BIGINT NOT NULL,
        contenido TEXT NOT NULL,
        created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        
        INDEX idx_comment_product (product_id),
        INDEX idx_comment_user (user_id),
        
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
        FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
    );

    -- Verificar que se creó correctamente
    DESCRIBE comments;

    SELECT 'Tabla comments creada exitosamente' AS status;

