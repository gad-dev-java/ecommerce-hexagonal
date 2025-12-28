\c products_service_db;

CREATE TABLE products
(
    id             UUID PRIMARY KEY,
    seller_id      UUID           NOT NULL,
    category_id    UUID           NOT NULL,
    title          VARCHAR(255)   NOT NULL,
    description    TEXT           NOT NULL,
    price          NUMERIC(10, 2) NOT NULL,
    currency       VARCHAR(3)     NOT NULL,
    stock          INTEGER        NOT NULL DEFAULT 0,
    condition_type VARCHAR(20)    NOT NULL CHECK (condition_type IN ('NEW', 'USED')),
    status         VARCHAR(20)             DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'PAUSED', 'SOLD_OUT')),
    image_url      VARCHAR(500),
    version        BIGINT                  DEFAULT 0,
    created_at     TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE INDEX idx_products_seller ON products (seller_id);
CREATE INDEX idx_products_category ON products (category_id);

INSERT INTO products (id, seller_id, category_id, title, description, price, currency, stock, condition_type, status, image_url, version) VALUES

('ee405be8-442c-4858-9a2b-1bb70a7ca753', '99a00274-5df6-4bf0-bc4c-7109cad4f476', '9e783c17-491d-4350-9622-802cf1988327', 'iPhone 14 Pro Max 256GB', 'El último iPhone con cámara de 48MP y chip A16.', 1200.00, 'PEN', 10, 'NEW', 'ACTIVE', 'http://img.com/iphone14.jpg', 0),
('5b63d583-ceee-4fcc-b699-9eeb254031e2', '99a00274-5df6-4bf0-bc4c-7109cad4f476', '9e783c17-491d-4350-9622-802cf1988327', 'Samsung Galaxy S23 Ultra', 'Samsung con S-Pen incluido y zoom 100x.', 1150.00, 'PEN', 15, 'NEW', 'ACTIVE', 'http://img.com/s23.jpg', 0),
('efb2070d-1223-4e5c-934e-9d02e5c8ee5b', '99a00274-5df6-4bf0-bc4c-7109cad4f476', '9e783c17-491d-4350-9622-802cf1988327', 'iPhone 11 64GB Usado', 'En buen estado, batería al 85%.', 300.00, 'PEN', 1, 'USED', 'ACTIVE', 'http://img.com/iphone11.jpg', 0),
('f0f16fe9-7f57-4c00-a678-b5e495d09ff3', '99a00274-5df6-4bf0-bc4c-7109cad4f476', '9e783c17-491d-4350-9622-802cf1988327', 'Xiaomi Redmi Note 12', 'Calidad precio, pantalla AMOLED.', 250.00, 'PEN', 50, 'NEW', 'ACTIVE', 'http://img.com/redmi.jpg', 0),

('64059a5c-79e6-4786-a7ed-14bdd53aa7f6', '823efd46-0caa-4d7b-8854-e75881514216', '06b211b3-5c7c-48f7-8c57-de7e552cec71', 'MacBook Air M1', 'Chip M1, 8GB RAM, 256GB SSD, Gris Espacial.', 900.00, 'PEN', 5, 'NEW', 'ACTIVE', 'http://img.com/macm1.jpg', 0),
('2395ca5c-7377-4b9d-99fa-400c41796719', '823efd46-0caa-4d7b-8854-e75881514216', '06b211b3-5c7c-48f7-8c57-de7e552cec71', 'Dell XPS 13 Plus', 'Pantalla OLED, Intel i7 12th Gen.', 1400.00, 'PEN', 2, 'NEW', 'PAUSED', 'http://img.com/dellxps.jpg', 0),
('59f02f28-b1a4-4950-9c66-c21a4c47ddd2', '823efd46-0caa-4d7b-8854-e75881514216', '06b211b3-5c7c-48f7-8c57-de7e552cec71', 'Lenovo ThinkPad Usada', 'Ideal para trabajo, i5 8va gen.', 400.00, 'PEN', 1, 'USED', 'ACTIVE', 'http://img.com/thinkpad.jpg', 0),

('68d574ed-fe1f-42b1-b065-bf1b6a2fdc3f', 'a4f8b511-d3ed-48bd-87b0-bade9bc10529', '96183117-c442-4887-ae6b-e2994ec6a5f3', 'Sony PlayStation 5', 'Versión con lector de discos, incluye God of War.', 550.00, 'PEN', 0, 'NEW', 'SOLD_OUT', 'http://img.com/ps5.jpg', 0),
('2e74d977-f0fe-4b31-8a30-63fa9da6b5a8', 'a4f8b511-d3ed-48bd-87b0-bade9bc10529', '96183117-c442-4887-ae6b-e2994ec6a5f3', 'Nintendo Switch OLED', 'Pantalla vibrante, modo portátil.', 350.00, 'PEN', 20, 'NEW', 'ACTIVE', 'http://img.com/switch.jpg', 0),
('190d0274-c027-419e-b974-aa79041ad54e', 'a4f8b511-d3ed-48bd-87b0-bade9bc10529', '96183117-c442-4887-ae6b-e2994ec6a5f3', 'Xbox Series S', 'Consola digital compacta.', 300.00, 'PEN', 10, 'NEW', 'ACTIVE', 'http://img.com/xboxs.jpg', 0),

('27ef555b-01d0-4137-8b9f-2cd0b78d5ab2', 'a36767fe-75f6-4fc6-b63a-29b347633dff', '7411cec3-7ab6-4ecf-bad9-3add2682e54f', 'Nike Air Force 1', 'Clásicas blancas, talla 42.', 110.00, 'PEN', 30, 'NEW', 'ACTIVE', 'http://img.com/af1.jpg', 0),
('6ec64c6d-59f5-4202-afe1-afc6ad9da608', 'a36767fe-75f6-4fc6-b63a-29b347633dff', '7411cec3-7ab6-4ecf-bad9-3add2682e54f', 'Adidas Ultraboost', 'Zapatillas de running máxima comodidad.', 180.00, 'PEN', 12, 'NEW', 'ACTIVE', 'http://img.com/ultra.jpg', 0),
('c279eb42-8254-403e-b036-93ed9e36cc48', 'a36767fe-75f6-4fc6-b63a-29b347633dff', '7411cec3-7ab6-4ecf-bad9-3add2682e54f', 'Vans Old Skool', 'Color negro, estilo urbano.', 75.00,'PEN',  25, 'NEW', 'ACTIVE', 'http://img.com/vans.jpg', 0),

('1e711556-92dc-4885-8518-0ac68f466e56', '7db4e08c-3e12-4975-bfdb-9997d160230a', '74f1efd9-c962-416d-a120-19e2a105ad64', 'Camiseta Selección Argentina', '3 estrellas, original Adidas.', 90.00, 'USD', 100, 'NEW', 'ACTIVE', 'http://img.com/arg.jpg', 0),
('76edf326-2e1c-4583-a549-2c899215ca56', '7db4e08c-3e12-4975-bfdb-9997d160230a', '74f1efd9-c962-416d-a120-19e2a105ad64', 'Pack 3 Remeras Básicas', 'Algodón premium, colores variados.', 30.00, 'USD', 200, 'NEW', 'ACTIVE', 'http://img.com/pack.jpg', 0),

('a9f55890-383a-42d1-a29a-0e7360566690', '6700830c-5f5e-4e79-a642-bc88f744f7b0', '2980ef48-d488-43db-b4b4-d19fd8000450', 'Taladro Percutor Bosch', '750w profesional con maletín.', 120.00, 'USD', 8, 'NEW', 'ACTIVE', 'http://img.com/taladro.jpg', 0),
('99a337e5-68cd-4aa1-98e1-fe4c8f2332da', '6700830c-5f5e-4e79-a642-bc88f744f7b0', '2980ef48-d488-43db-b4b4-d19fd8000450', 'Set de Destornilladores', 'Kit de 50 piezas magnéticas.', 25.00, 'USD', 50, 'NEW', 'ACTIVE', 'http://img.com/kit.jpg', 0),

('1e26723c-63f2-4631-b182-eaaf27622a30', '26278217-627b-4a06-b0e9-278d92e4fcf7', 'a3d701cb-7bdc-4070-b25b-4eb2c0615f09', 'Sofá Cama 3 Cuerpos', 'Tela gris, estructura de madera.', 400.00, 'USD', 2, 'NEW', 'ACTIVE', 'http://img.com/sofa.jpg', 0),
('c4cecb20-da22-4562-8836-dddbc13c489f', '26278217-627b-4a06-b0e9-278d92e4fcf7', '610608df-9225-4333-bab7-c55ba408bb16', 'Monitor Gamer 27"', '144Hz, 1ms, IPS. (Categoría Raíz Tecnología)', 300.00, 'USD', 10, 'NEW', 'ACTIVE', 'http://img.com/monitor.jpg', 0),
('baa30a02-402e-4a53-ac88-c06c08abad2e', '26278217-627b-4a06-b0e9-278d92e4fcf7', '96183117-c442-4887-ae6b-e2994ec6a5f3', 'PS4 Fat 500GB', 'Consola usada con 1 control, detalle estético.', 150.00, 'USD', 1, 'USED', 'ACTIVE', 'http://img.com/ps4.jpg', 0);