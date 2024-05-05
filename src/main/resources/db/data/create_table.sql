CREATE TABLE cars
(
    id              SERIAL PRIMARY KEY,
    brand           VARCHAR(255),
    model           VARCHAR(255),
    production_date DATE,
    price           FLOAT,
    type            VARCHAR(255),
    country         VARCHAR(255),
    image_url       VARCHAR(255)
);