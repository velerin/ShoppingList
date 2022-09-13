DROP DATABASE  IF EXISTS `web-shopping-list`;

CREATE DATABASE  IF NOT EXISTS `web-shopping-list`;
USE `web-shopping-list`;

CREATE TABLE users (
  id INT AUTO_INCREMENT NOT NULL,
   first_name VARCHAR(255) NOT NULL,
   last_name VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   user_name VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   enabled BIT(1) NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users ADD CONSTRAINT uc_users_username UNIQUE (user_name);

ALTER TABLE users ADD CONSTRAINT uc_users_email UNIQUE (email);

INSERT INTO `users` (username,password,first_name,last_name,email,enabled)
VALUES
('john','$2a$12$TCQZHxH4oWniBX1nDDYere9UszxzrvBkyeQSnIYE158jQuQ7X2uUO','John','Doe','john@luv2code.com',1),
('mary','$2a$12$TCQZHxH4oWniBX1nDDYere9UszxzrvBkyeQSnIYE158jQuQ7X2uUO','Mary','Public','mary@luv2code.com',1),
('susan','$2a$12$TCQZHxH4oWniBX1nDDYere9UszxzrvBkyeQSnIYE158jQuQ7X2uUO','Susan','Adams','susan@luv2code.com',1);

CREATE TABLE authorities (
  id INT AUTO_INCREMENT NOT NULL,
   username VARCHAR(255) NULL,
   authority VARCHAR(255) NULL,
   user_id INT NULL,
   CONSTRAINT pk_authorities PRIMARY KEY (id)
);

INSERT INTO `authorities` (username,authority,user_id)
VALUES
('john','ROLE_USER',1),('susan','ROLE_USER',3),('mary','ROLE_USER',2),('mary','ROLE_MANAGER',2),('susan','ROLE_ADMIN',3);

CREATE TABLE lists (
  id INT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NOT NULL,
   user_id INT NOT NULL,
   CONSTRAINT pk_lists PRIMARY KEY (id)
);

INSERT INTO `lists` (title,user_id)
VALUES
('List 1 User 1',1),('List 2 User 1',1),('List 1 User 2',2),('List 1 User 2',2),('List 1 User 3',3),('List 1 User 3',3);

CREATE TABLE products (
  id INT AUTO_INCREMENT NOT NULL,
   amount INT NOT NULL,
   product_name VARCHAR(255) NOT NULL,
   product_id INT NOT NULL,
   CONSTRAINT pk_products PRIMARY KEY (id)
);

INSERT INTO `products` (amount,product_name,product_id)
VALUES
(1,'Cheese',1),
(2,'Milk',2),
(1,'Jogurt',3),
(2,'Cheese',4),
(3,'Milk',5),
(2,'Jogurt',6),
(5,'Cheese',1),
(6,'Milk',2),
(7,'Jogurt',3);

ALTER TABLE authorities ADD CONSTRAINT FK_AUTHORITIES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE products ADD CONSTRAINT FK_PRODUCTS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES lists (id);

ALTER TABLE lists ADD CONSTRAINT FK_LISTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);
