\c notifications_service_db;

CREATE TABLE notifications
(
    id         UUID PRIMARY KEY,
    user_id    UUID        NOT NULL,
    type       VARCHAR(20) NOT NULL CHECK (type IN
                                           ('ORDER_STATUS', 'PAYMENT_RECEIVED', 'SHIPMENT_UPDATE', 'PROMOTION')),
    message    TEXT        NOT NULL,
    is_read    BOOLEAN     DEFAULT FALSE,
    created_at TIMESTAMP   DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_notifications_user ON notifications (user_id);