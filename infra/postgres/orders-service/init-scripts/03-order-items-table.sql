\c orders_service_db;

CREATE TABLE order_items
(
    id            UUID PRIMARY KEY,
    order_id      UUID           NOT NULL,
    product_id    UUID           NOT NULL,
    product_title VARCHAR(255)   NOT NULL,
    quantity      INTEGER        NOT NULL,
    unit_price    NUMERIC(10, 2) NOT NULL,
    subtotal      NUMERIC(10, 2) NOT NULL,

    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);

CREATE INDEX idx_orders_buyer ON orders (buyer_id);
CREATE INDEX idx_orders_seller ON orders (seller_id);