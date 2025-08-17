CREATE TABLE inventory (
    product_id VARCHAR(255) PRIMARY KEY,
    quantity INTEGER NOT NULL,
    version BIGINT NOT NULL,
    reserved_quantity INTEGER NOT NULL DEFAULT 0,
    status VARCHAR(50) NOT NULL,
    reorder_point INTEGER NOT NULL,
    max_stock_level INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_inventory_status ON inventory(status);
