CREATE TABLE IF NOT EXISTS cars
(
    id              SERIAL PRIMARY KEY,
    brand           VARCHAR(255)   NOT NULL,
    model           VARCHAR(255)   NOT NULL,
    production_date DATE           NOT NULL,
    price           NUMERIC(10, 2) NOT NULL,
    type            VARCHAR(255)   NOT NULL,
    country         VARCHAR(255)   NOT NULL,
    image_url       VARCHAR(255)   NOT NULL
);
INSERT INTO cars (brand, model, production_date, price, type, country, image_url)
VALUES ('Renault', 'Arkanam', '2023-01-01', 25000, 'SUV', 'France', '../resources/images/Arcanum.jpg'),
       ('BMW', 'X4', '2022-10-05', 52000, 'SUV', 'Germany', '../resources/images/BMW_X4.jpg'),
       ('BMW', '8 Series', '2020-10-10', 80000, 'Coupe', 'Germany', '../resources/images/BMW_8.jpg'),
       ('BMW', '3 Series', '2022-05-20', 45000, 'Sedan', 'Germany', '../resources/images/BMW_3.jpg'),
       ('BMW', '1 Series', '2023-01-01', 35000, 'Hatchback', 'Germany', '../resources/images/BMW_1.jpg'),
       ('Renault', 'Sandero Stepway', '2022-07-10', 19000, 'SUV', 'Romania', '../resources/images/Sandero_Stepway.jpg'),
       ('Renault', 'Master', '2020-10-10', 40000, 'Van', 'France', '../resources/images/Master.jpg'),
       ('BMW', 'X6', '2021-09-28', 65000, 'SUV', 'Germany', '../resources/images/BMW_X6.jpg'),
       ('BMW', '2 Series', '2022-03-15', 40000, 'Coupe', 'Germany', '../resources/images/BMW_2.jpg'),
       ('BMW', '5 Series', '2022-09-05', 55000, 'Sedan', 'Germany', '../resources/images/BMW_5.jpg'),
       ('Renault', 'Captur', '2023-02-28', 28000, 'SUV', 'France', '../resources/images/Captur.jpg'),
       ('BMW', '7 Series', '2023-02-28', 70000, 'Sedan', 'Germany', '../resources/images/BMW_7.jpg'),
       ('BMW', 'X5', '2022-11-01', 58000, 'SUV', 'Germany', '../resources/images/BMW_X5.jpg'),
       ('BMW', 'X3', '2022-08-10', 48000, 'SUV', 'Germany', '../resources/images/BMW_X3.jpg'),
       ('Renault', 'Logan Stepway', '2022-09-05', 18000, 'SUV', 'Romania', '../resources/images/Logan_Stepway.jpg'),
       ('Renault', 'Sandero', '2022-05-20', 17000, 'Hatchback', 'Romania', '../resources/images/Sandero.jpg'),
       ('Renault', 'Logan', '2022-03-15', 15000, 'Sedan', 'Romania', '../resources/images/Logan.jpg'),
       ('BMW', 'X2', '2023-06-20', 42000, 'SUV', 'Germany', '../resources/images/BMW_X2.jpg'),
       ('BMW', 'X1', '2022-04-15', 38000, 'SUV', 'Germany', '../resources/images/BMW_X1.jpg'),
       ('BMW', '6 Series', '2021-12-01', 60000, 'Coupe', 'Germany', '../resources/images/BMW_6.jpg'),
       ('BMW', '4 Series', '2022-07-10', 50000, 'Coupe', 'Germany', '../resources/images/BMW_4.jpg'),
       ('Renault', 'Duster', '2021-12-01', 30000, 'SUV', 'France', '../resources/images/Duster.jpg');
