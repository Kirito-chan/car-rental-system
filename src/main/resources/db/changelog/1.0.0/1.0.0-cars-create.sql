CREATE TABLE car
(
    id                        SERIAL PRIMARY KEY,
    make                      VARCHAR(255) NOT NULL,
    model                     VARCHAR(255) NOT NULL,
    total_seats               INTEGER      NOT NULL,
    is_automatic_transmission BOOLEAN      NOT NULL,
    is_rented                 BOOLEAN      NOT NULL,
    total_kilometers_driven   INTEGER      NOT NULL
);