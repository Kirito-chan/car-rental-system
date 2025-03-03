CREATE TABLE rental
(
    id                SERIAL PRIMARY KEY,
    car_id            INTEGER NOT NULL UNIQUE,
    customer_id       INTEGER NOT NULL,
    kilometers_driven INTEGER
);