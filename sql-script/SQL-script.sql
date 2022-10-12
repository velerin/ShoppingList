CREATE USER 'springstudent'@'localhost' IDENTIFIED BY 'springstudent';

GRANT ALL PRIVILEGES ON * . * TO 'springstudent'@'localhost';

ALTER USER 'springstudent'@'localhost' IDENTIFIED WITH mysql_native_password BY 'springstudent';

DROP DATABASE  IF EXISTS `web-shopping-list`;

CREATE DATABASE  IF NOT EXISTS `web-shopping-list`;
USE `web-shopping-list`;

CREATE TABLE users (
  id INT AUTO_INCREMENT NOT NULL,
   first_name VARCHAR(255) NOT NULL,
   last_name VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   username VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   enabled boolean NOT NULL,
   `locked` boolean NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);


ALTER TABLE users ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users ADD CONSTRAINT uc_users_username UNIQUE (username);

INSERT INTO `users` (username, password, first_name, last_name, email, enabled,locked)
VALUES
('john','$2a$12$TCQZHxH4oWniBX1nDDYere9UszxzrvBkyeQSnIYE158jQuQ7X2uUO','John','Doe','john@gmail.com',1,0),
('mary','$2a$12$TCQZHxH4oWniBX1nDDYere9UszxzrvBkyeQSnIYE158jQuQ7X2uUO','Mary','Public','mary@gmail.com',1,0),
('susan','$2a$12$TCQZHxH4oWniBX1nDDYere9UszxzrvBkyeQSnIYE158jQuQ7X2uUO','Susan','Adams','susan@gmail.com',1,0);

CREATE TABLE authorities (
  id INT AUTO_INCREMENT NOT NULL,
   username VARCHAR(255) NULL,
   authority VARCHAR(255) NULL,
   user_id INT NULL,
   CONSTRAINT pk_authorities PRIMARY KEY (id)
);

INSERT INTO `authorities` (username, authority, user_id)
VALUES
('john','ROLE_USER',1),('susan','ROLE_USER',3),('mary','ROLE_USER',2),('mary','ROLE_MANAGER',2),('susan','ROLE_ADMIN',3);

CREATE TABLE lists (
  id INT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NOT NULL,
   user_id INT NULL,
   CONSTRAINT pk_lists PRIMARY KEY (id)
);

INSERT INTO `lists` (title,user_id)
VALUES
('List 1 User 1',1),('List 2 User 1',1),('List 1 User 2',2),('List 1 User 2',2),('List 1 User 3',3),('List 1 User 3',3);

CREATE TABLE products (
  id INT AUTO_INCREMENT NOT NULL,
   amount DOUBLE NOT NULL,
   product_name VARCHAR(255) NOT NULL,
   price_per_piece DOUBLE NOT NULL,
   currency VARCHAR(255) NOT NULL,
   product_list_id INT NULL,
   CONSTRAINT pk_products PRIMARY KEY (id)
);


INSERT INTO `products` (amount, product_name, price_per_piece, currency, product_list_id)
VALUES
(1,'Bree',1.00,'PLN',1),
(2,'Łaciate',1.00,'PLN',2),
(1,'Jogobela',1.00,'PLN',3),
(2,'White cheese',1.00,'PLN',4),
(3,'Soja Milk',1.00,'PLN',5),
(2,'Milka jogurt',1.00,'PLN',6),
(5,'Blue cheese',1.00,'PLN',1),
(6,'Almond milk',1.00,'PLN',2),
(7,'Jogurt',1.00,'PLN',3);

ALTER TABLE authorities ADD CONSTRAINT FK_AUTHORITIES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE lists ADD CONSTRAINT FK_LISTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE products ADD CONSTRAINT FK_PRODUCTS_ON_PRODUCT_LIST FOREIGN KEY (product_list_id) REFERENCES lists (id);

