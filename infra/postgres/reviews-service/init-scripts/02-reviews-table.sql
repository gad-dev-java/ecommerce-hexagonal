\c reviews_service_db;

CREATE TABLE reviews
(
    id         UUID PRIMARY KEY,
    product_id UUID    NOT NULL,
    user_id    UUID    NOT NULL,
    order_id   UUID    NOT NULL,
    rating     INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment    TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_reviews_product ON reviews(product_id);