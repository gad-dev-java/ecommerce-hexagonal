\c products_service_db;

CREATE TABLE categories
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    parent_id  UUID,
    created_at TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (parent_id) REFERENCES categories (id)
);

INSERT INTO categories (id, name, parent_id) VALUES
                                                 ('610608df-9225-4333-bab7-c55ba408bb16', 'Tecnología', NULL),
                                                 ('f4188738-d954-4544-a1e9-d3d3751342f3', 'Ropa y Moda', NULL),
                                                 ('a3d701cb-7bdc-4070-b25b-4eb2c0615f09', 'Hogar y Muebles', NULL),
                                                 ('04177cc1-23ab-44bd-8b93-89ef7815657e', 'Videojuegos', NULL);

INSERT INTO categories (id, name, parent_id) VALUES
('9e783c17-491d-4350-9622-802cf1988327', 'Celulares y Smartphones', '610608df-9225-4333-bab7-c55ba408bb16'),
('06b211b3-5c7c-48f7-8c57-de7e552cec71', 'Laptops y Computación', '610608df-9225-4333-bab7-c55ba408bb16'),
('7411cec3-7ab6-4ecf-bad9-3add2682e54f', 'Zapatillas', 'f4188738-d954-4544-a1e9-d3d3751342f3'),
('74f1efd9-c962-416d-a120-19e2a105ad64', 'Camisetas', 'f4188738-d954-4544-a1e9-d3d3751342f3'),
('2980ef48-d488-43db-b4b4-d19fd8000450', 'Herramientas', 'a3d701cb-7bdc-4070-b25b-4eb2c0615f09'),
('96183117-c442-4887-ae6b-e2994ec6a5f3', 'Consolas', '04177cc1-23ab-44bd-8b93-89ef7815657e');