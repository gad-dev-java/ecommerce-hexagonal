\c orders_service_db;

CREATE TABLE orders
(
    id                        UUID PRIMARY KEY,
    order_number              VARCHAR(50) UNIQUE NOT NULL,
    buyer_id                  UUID               NOT NULL,
    seller_id                 UUID               NOT NULL,
    total_amount              NUMERIC(10, 2)     NOT NULL,
    shipping_cost             NUMERIC(8, 2)    DEFAULT 0.00,
    status                    VARCHAR(20)      DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED', 'CANCELLED')),
    shipping_address_snapshot JSONB              NOT NULL,

    created_at                TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    updated_at                TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);
