-- Crear tabla de favoritos si no existe
-- Ejecutar este script en MySQL

USE voltmarket_db;

-- Crear tabla favorites
CREATE TABLE IF NOT EXISTS favorites (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    
    -- Índice para búsquedas por usuario
    INDEX idx_fav_user (user_id),
    
    -- Constraint único: un usuario solo puede marcar un producto como favorito una vez
    UNIQUE KEY uk_fav_user_product (user_id, product_id),
    
    -- Relaciones con otras tablas
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- Verificar que se creó correctamente
DESCRIBE favorites;

SELECT 'Tabla favorites creada exitosamente' AS status;

