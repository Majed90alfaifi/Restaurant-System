-- Restaurant System database schema
-- Usage: mysql -u root -p < Project252.sql

CREATE DATABASE IF NOT EXISTS cpit252project_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE cpit252project_db;

-- Registered users. The account with phone '0000' signs in as the admin.
CREATE TABLE IF NOT EXISTS customer (
    customerPhone VARCHAR(20)  NOT NULL,
    customerName  VARCHAR(100) NOT NULL,
    customerPass  VARCHAR(255) NOT NULL,
    PRIMARY KEY (customerPhone)
);

-- Menu items. Column order matters: the app reads columns by position
-- (1 = id, 2 = name, 3 = price, 4 = quantity).
CREATE TABLE IF NOT EXISTS menu_items (
    items_id      INT          NOT NULL,
    item_name     VARCHAR(100) NOT NULL,
    item_price    FLOAT        NOT NULL,
    item_quantity INT          NOT NULL DEFAULT 0,
    PRIMARY KEY (items_id),
    UNIQUE KEY uq_menu_items_name (item_name)
);

-- The current customer's order. Cleared at the start of each order.
CREATE TABLE IF NOT EXISTS cart (
    item_id       INT   NOT NULL,
    item_quantity INT   NOT NULL,
    total_price   FLOAT NOT NULL,
    PRIMARY KEY (item_id),
    CONSTRAINT fk_cart_item FOREIGN KEY (item_id)
        REFERENCES menu_items (items_id) ON DELETE CASCADE
);

-- Admin account (change this password before using the app anywhere but locally)
INSERT IGNORE INTO customer (customerPhone, customerName, customerPass)
VALUES ('0000', 'Admin', 'admin');

-- Sample menu
INSERT IGNORE INTO menu_items (items_id, item_name, item_price, item_quantity) VALUES
    (1, 'Burger',     25.0, 50),
    (2, 'Pizza',      40.0, 30),
    (3, 'Shawarma',   15.0, 60),
    (4, 'Fries',      10.0, 80),
    (5, 'Juice',       8.0, 40);
