\c users_service_db;

CREATE TABLE addresses
(
    id          UUID PRIMARY KEY,
    user_id     UUID         NOT NULL,
    alias       VARCHAR(50),
    street      VARCHAR(255) NOT NULL,
    city        VARCHAR(100) NOT NULL,
    state       VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20)  NOT NULL,
    country     VARCHAR(50)  NOT NULL,
    is_default  BOOLEAN          DEFAULT FALSE,
    created_at  TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE INDEX idx_users_email ON users (email);
CREATE INDEX idx_addresses_user ON addresses (user_id);