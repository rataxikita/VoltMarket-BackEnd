-- Agregar columna SKU a la tabla products
-- Ejecutar este script directamente en MySQL

USE voltmarket_db;

-- Agregar la columna sku
ALTER TABLE products ADD COLUMN sku VARCHAR(80) UNIQUE AFTER image_url;

-- Verificar que se agregó correctamente
DESCRIBE products;

