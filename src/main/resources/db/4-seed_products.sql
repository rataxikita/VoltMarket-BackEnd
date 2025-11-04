-- Script de datos semilla para productos de partes de scooter eléctrico
-- Productos adicionales basados en las categorías existentes (1-8)
-- Compatible con Xiaomi M365, Ninebot, Segway y otros scooters eléctricos

-- ============================================
-- INSERTAR CATEGORÍAS
-- ============================================
INSERT INTO categories (id, nombre, descripcion) VALUES 
(1, 'BATERÍAS', 'Baterías de litio y sistemas de almacenamiento de energía para scooters eléctricos. Compatible con modelos Xiaomi, Ninebot, Segway y otros.'),
(2, 'FRENOS', 'Sistemas de frenado, discos, pastillas, cables y manetas de freno para scooters eléctricos.'),
(3, 'NEUMÁTICOS', 'Neumáticos inflables y sólidos, cámaras de aire y kits de reparación para diferentes tamaños de rueda.'),
(4, 'MOTORES', 'Motores brushless de diferentes potencias para scooters eléctricos. Desde 150W hasta 500W.'),
(5, 'ILUMINACIÓN', 'Luces delanteras, traseras, LED RGB y accesorios de iluminación para mayor visibilidad y seguridad.'),
(6, 'MANILLAR Y ACCESORIOS', 'Manillares, grips, soportes para móviles, timbres y accesorios para el manillar del scooter.'),
(7, 'ELECTRÓNICA Y CONTROLADORES', 'Displays, controladores de motor, BMS, aceleradores y componentes electrónicos para gestión del scooter.'),
(8, 'HERRAMIENTAS Y ACCESORIOS', 'Herramientas de mantenimiento, infladores, bolsos de transporte, candados, guardabarros y accesorios varios.');

-- ============================================
-- INSERTAR PRODUCTOS
-- ============================================

-- Categoría 1: BATERÍAS
INSERT INTO products (nombre, descripcion, precio, stock, category_id, marca, image_url, sku, activo, created_at, updated_at) VALUES 
('Batería Ninebot ES2 36V 5.2Ah', 'Batería de litio original para Ninebot ES1/ES2, 187.2Wh', 149990.00, 3, 1, 'Ninebot', 'https://http2.mlstatic.com/D_NQ_NP_2X_8901.jpg', 'BAT-002', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Batería Xiaomi M365 Pro 36V 12.8Ah', 'Batería extendida para Xiaomi M365 Pro, 460.8Wh', 229990.00, 2, 1, 'Xiaomi', 'https://http2.mlstatic.com/D_NQ_NP_2X_9234.jpg', 'BAT-003', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Batería Compatible Ninebot Max 36V 15Ah', 'Batería de alta capacidad para Ninebot Max, 540Wh', 279990.00, 2, 1, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_7821.jpg', 'BAT-004', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Batería Xiaomi Essential 36V 5.1Ah', 'Batería compacta para Xiaomi Essential, 183.6Wh', 139990.00, 4, 1, 'Xiaomi', 'https://http2.mlstatic.com/D_NQ_NP_2X_6543.jpg', 'BAT-005', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Batería Genérica 36V 10Ah Universal', 'Batería compatible con múltiples modelos de scooter', 199990.00, 5, 1, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_5678.jpg', 'BAT-006', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00');

-- Categoría 2: FRENOS
INSERT INTO products (nombre, descripcion, precio, stock, category_id, marca, image_url, sku, activo, created_at, updated_at) VALUES 
('Freno de disco mecánico trasero M365', 'Sistema de frenado mecánico para rueda trasera Xiaomi M365', 29990.00, 8, 2, 'Xiaomi', 'https://http2.mlstatic.com/D_NQ_NP_2X_3456.jpg', 'FRE-003', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Kit Pastillas de Freno Ninebot', 'Juego de pastillas de freno compatibles con Ninebot ES', 15990.00, 12, 2, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_4567.jpg', 'FRE-004', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Disco de freno 120mm universal', 'Disco de freno compatible con M365 y Ninebot', 24990.00, 10, 2, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_8765.jpg', 'FRE-005', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Cable de freno reforzado M365', 'Cable de freno de acero inoxidable para Xiaomi M365', 8990.00, 15, 2, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_2345.jpg', 'FRE-006', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Maneta de freno izquierda Ninebot', 'Maneta de freno de repuesto para Ninebot ES', 18990.00, 7, 2, 'Ninebot', 'https://http2.mlstatic.com/D_NQ_NP_2X_5432.jpg', 'FRE-007', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00');

-- Categoría 3: NEUMÁTICOS
INSERT INTO products (nombre, descripcion, precio, stock, category_id, marca, image_url, sku, activo, created_at, updated_at) VALUES 
('Neumático 10" Xiaomi M365 Pro', 'Neumático inflable 10 pulgadas para M365 Pro', 42990.00, 6, 3, 'Xiaomi', 'https://http2.mlstatic.com/D_NQ_NP_2X_6789.jpg', 'NEU-002', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Neumático 8" Sólido Ninebot ES', 'Neumático sólido antipinchazos para Ninebot ES1/ES2', 28990.00, 9, 3, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_4321.jpg', 'NEU-003', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Neumático 10" Sólido con Panal', 'Neumático honeycomb antipinchazos para M365', 45990.00, 8, 3, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_7654.jpg', 'NEU-004', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Cámara 10" Xiaomi M365', 'Cámara de aire de repuesto para neumático 10"', 9890.00, 20, 3, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_3210.jpg', 'NEU-005', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Cámara 8.5" Reforzada', 'Cámara de aire reforzada para neumático 8.5"', 11990.00, 18, 3, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_8901.jpg', 'CAM-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Kit Reparación Neumático', 'Kit completo con parches y herramientas', 14990.00, 25, 3, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_5643.jpg', 'KIT-NEU-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00');

-- Categoría 4: MOTORES
INSERT INTO products (nombre, descripcion, precio, stock, category_id, marca, image_url, sku, activo, created_at, updated_at) VALUES 
('Motor 300W Xiaomi M365', 'Motor brushless 300W compatible con Xiaomi M365', 189990.00, 3, 4, 'Xiaomi', 'https://http2.mlstatic.com/D_NQ_NP_2X_6781.jpg', 'MOT-002', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Motor 350W Ninebot ES2', 'Motor brushless 350W original para Ninebot ES2', 209990.00, 2, 4, 'Ninebot', 'https://http2.mlstatic.com/D_NQ_NP_2X_5432.jpg', 'MOT-003', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Motor 500W Alta Potencia', 'Motor brushless 500W universal para scooters', 289990.00, 2, 4, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_7890.jpg', 'MOT-004', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Motor 150W Compacto', 'Motor económico para scooters básicos', 129990.00, 5, 4, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_2109.jpg', 'MOT-005', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00');

-- Categoría 5: ILUMINACIÓN
INSERT INTO products (nombre, descripcion, precio, stock, category_id, marca, image_url, sku, activo, created_at, updated_at) VALUES 
('Luz Delantera LED 10W', 'Faro LED de alta intensidad con sistema de montaje', 22990.00, 8, 5, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_6543.jpg', 'LUZ-DEL-002', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Luz Trasera con Freno Automático', 'Luz LED trasera con sensor de frenado', 16990.00, 12, 5, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_4321.jpg', 'LUZ-TRA-002', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Tira LED RGB para deck', 'Iluminación decorativa multicolor para plataforma', 19990.00, 10, 5, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_8765.jpg', 'LUZ-RGB-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Reflectores laterales adhesivos', 'Set de 4 reflectores para mayor visibilidad', 5990.00, 30, 5, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_1234.jpg', 'REF-LAT-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Luz delantera Xiaomi M365 Original', 'Faro LED original para Xiaomi M365', 24990.00, 6, 5, 'Xiaomi', 'https://http2.mlstatic.com/D_NQ_NP_2X_9876.jpg', 'LUZ-XIA-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00');

-- Categoría 6: MANILLAR Y ACCESORIOS
INSERT INTO products (nombre, descripcion, precio, stock, category_id, marca, image_url, sku, activo, created_at, updated_at) VALUES 
('Manillar Plegable Reforzado', 'Manillar de aluminio con mecanismo de plegado rápido', 49990.00, 5, 6, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_5678.jpg', 'MAN-002', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Grips de Silicona Antideslizante', 'Puños ergonómicos de silicona para mayor agarre', 8990.00, 20, 6, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_3456.jpg', 'GRI-002', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Grips con Indicador de Batería', 'Puños inteligentes con display LED de batería', 18990.00, 8, 6, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_7890.jpg', 'GRI-003', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Soporte de móvil giratorio', 'Soporte universal para smartphone con rotación 360°', 16990.00, 15, 6, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_4567.jpg', 'SOP-MOV-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Timbre eléctrico con bocina', 'Timbre/bocina eléctrica de 120dB', 12990.00, 18, 6, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_6789.jpg', 'TIM-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Espejos retrovisores ajustables', 'Par de espejos retrovisores con montaje universal', 19990.00, 12, 6, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_2345.jpg', 'ESP-PAR-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00');

-- Categoría 7: ELECTRÓNICA Y CONTROLADORES
INSERT INTO products (nombre, descripcion, precio, stock, category_id, marca, image_url, sku, activo, created_at, updated_at) VALUES 
('Display LCD Xiaomi M365', 'Pantalla LCD original con velocímetro y nivel de batería', 35990.00, 4, 7, 'Xiaomi', 'https://http2.mlstatic.com/D_NQ_NP_2X_8901.jpg', 'DIS-002', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Controlador 48V 25A', 'Controlador brushless 48V con protección térmica', 109990.00, 3, 7, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_5432.jpg', 'CON-002', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Controlador 36V 20A Ninebot', 'Controlador compatible con Ninebot ES1/ES2/ES4', 89990.00, 4, 7, 'Ninebot', 'https://http2.mlstatic.com/D_NQ_NP_2X_7654.jpg', 'CON-003', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('BMS 36V 30A con Bluetooth', 'Sistema de gestión de batería con monitoreo por app', 54990.00, 6, 7, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_3210.jpg', 'BMS-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Display LED minimalista', 'Pantalla LED compacta con indicadores básicos', 24990.00, 10, 7, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_6789.jpg', 'DIS-LED-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Acelerador tipo gatillo', 'Acelerador de pulgar ergonómico para manillar', 14990.00, 15, 7, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_4321.jpg', 'ACE-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00');

-- Categoría 8: HERRAMIENTAS Y ACCESORIOS
INSERT INTO products (nombre, descripcion, precio, stock, category_id, marca, image_url, sku, activo, created_at, updated_at) VALUES 
('Kit Herramientas Básico', 'Set de llaves Allen y destornilladores para mantenimiento', 19990.00, 12, 8, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_8765.jpg', 'KIT-BAS-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Inflador portátil eléctrico', 'Compresor de aire portátil recargable con display digital', 34990.00, 8, 8, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_5432.jpg', 'INF-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Bolso de transporte resistente', 'Bolso con asas y correa para transportar scooter', 39990.00, 6, 8, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_2109.jpg', 'BOL-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Candado de disco con alarma', 'Candado de seguridad con alarma de 110dB', 44990.00, 10, 8, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_7890.jpg', 'CAN-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Guardabarros delantero universal', 'Guardabarros ajustable para rueda delantera', 12990.00, 20, 8, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_3456.jpg', 'GUA-DEL-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Guardabarros trasero con luz', 'Guardabarros trasero con luz LED integrada', 16990.00, 18, 8, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_6543.jpg', 'GUA-TRA-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Caballete lateral reforzado', 'Soporte lateral de aluminio con muelle de seguridad', 14990.00, 15, 8, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_9876.jpg', 'CAB-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Amortiguador de suspensión trasero', 'Amortiguador ajustable para suspensión trasera', 59990.00, 5, 8, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_1234.jpg', 'AMO-TRA-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00'),
('Cinta antideslizante para deck', 'Cinta de agarre autoadhesiva 84cm x 23cm', 8990.00, 30, 8, 'Generic', 'https://http2.mlstatic.com/D_NQ_NP_2X_5678.jpg', 'CIN-001', 1, '2025-10-25 00:24:43', '2025-10-25 00:25:00');
