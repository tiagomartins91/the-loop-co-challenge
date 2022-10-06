CREATE TABLE IF NOT EXISTS brand
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS price
(
    id          SERIAL PRIMARY KEY,
    brand_id    int8 REFERENCES brand(id) NOT NULL,
    start_date  TIMESTAMP,
    end_date    TIMESTAMP,
    price_list  INTEGER NOT NULL,
    product_id  INTEGER NOT NULL,
    priority    INTEGER NOT NULL,
    price       DECIMAL(4, 2) NOT NULL,
    curr        VARCHAR(10) NOT NULL
);