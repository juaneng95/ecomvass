-- CREATE TABLE PRICES
CREATE TABLE IF NOT EXISTS ecomvass_schema.price (
 	id bigint NOT NULL AUTO_INCREMENT,
 	brand_id bigint NOT NULL DEFAULT 0,
 	start_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    end_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 	price_list integer NOT NULL DEFAULT 0,
 	product_id integer NOT NULL DEFAULT 00000,
 	priority smallint NOT NULL DEFAULT 0,
 	price float NOT NULL DEFAULT 0.00,
 	curr varchar(50) NOT NULL DEFAULT 'Unassigned',
 	CONSTRAINT prices_pk PRIMARY KEY (id)
);

-- INSERT INTO PRICES
INSERT INTO ecomvass_schema.price
    (brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES
    (1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
    (1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
    (1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
    (1, '2020-06-15 16:00:00', '2020-12-31 23:59:57', 4, 35455, 1, 38.95, 'EUR'),
    (1, '2020-06-21 11:00:00', '2020-07-31 01:01:00', 2, 35455, 1, 80.99, 'EUR'),
    (2, '2020-06-15 16:00:00', '2020-12-31 23:59:58', 4, 35455, 0, 10.99, 'USD'),
    (2, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 0, 12.99, 'USD');