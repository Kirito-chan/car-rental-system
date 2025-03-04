CREATE TABLE rental
(
    id                SERIAL PRIMARY KEY,
    car_id            INTEGER NOT NULL UNIQUE,
    customer_id       INTEGER NOT NULL,
    kilometers_driven INTEGER,

    -- Add Foreign Key Constraints
    CONSTRAINT fk_rental_car FOREIGN KEY (car_id) REFERENCES car (id) ON DELETE CASCADE,
    CONSTRAINT fk_rental_customer FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);