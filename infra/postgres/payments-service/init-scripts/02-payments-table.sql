\c payments_service_db;

CREATE TABLE payments
(
    id             UUID PRIMARY KEY,
    order_id       UUID           NOT NULL,
    user_id        UUID           NOT NULL,
    amount         NUMERIC(10, 2) NOT NULL,
    payment_method VARCHAR(20)    NOT NULL CHECK (payment_method IN
                                                  ('CREDIT_CARD', 'DEBIT_CARD', 'PAYPAL', 'MERCADOPAGO', 'STRIPE')),
    status         VARCHAR(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED')),
    transaction_id VARCHAR(255),
    created_at     TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP   DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_payments_order ON payments (order_id);