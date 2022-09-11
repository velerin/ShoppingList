DROP DATABASE  IF EXISTS `web-shopping-list`;

CREATE DATABASE  IF NOT EXISTS `web-shopping-list`;
USE `web-shopping-list`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE users (
  id INT AUTO_INCREMENT NOT NULL,
   first_name VARCHAR(255) NULL,
   last_name VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   username VARCHAR(255) NULL,
   password VARCHAR(255) NULL,
   enabled tinyint(1) NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT uc_users_email UNIQUE (email);

INSERT INTO `users` (username,password,first_name,last_name,email,enabled)
VALUES
('john','$2a$12$TCQZHxH4oWniBX1nDDYere9UszxzrvBkyeQSnIYE158jQuQ7X2uUO','John','Doe','john@luv2code.com',1),
('mary','$2a$12$TCQZHxH4oWniBX1nDDYere9UszxzrvBkyeQSnIYE158jQuQ7X2uUO','Mary','Public','mary@luv2code.com',1),
('susan','$2a$12$TCQZHxH4oWniBX1nDDYere9UszxzrvBkyeQSnIYE158jQuQ7X2uUO','Susan','Adams','susan@luv2code.com',1);


DROP TABLE IF EXISTS `authorities`;
CREATE TABLE authorities (
  id INT AUTO_INCREMENT NOT NULL,
   username VARCHAR(255) NULL,
   authority VARCHAR(255) NULL,
   userName_id INT NULL,
   CONSTRAINT pk_authorities PRIMARY KEY (id)
);

INSERT INTO `authorities` (username,authority,userName_id)
VALUES
('john','ROLE_USER',1),('susan','ROLE_USER',3),('mary','ROLE_USER',2),('mary','ROLE_MANAGER',2),('susan','ROLE_ADMIN',3);

DROP TABLE IF EXISTS `lists`;
CREATE TABLE lists (
  id INT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NULL,
   list_id INT NULL,
   CONSTRAINT pk_list PRIMARY KEY (id)
);

INSERT INTO `lists` (title,list_id)
VALUES
('List 1 User 1',1),('List 2 User 1',1),('List 1 User 2',2),('List 1 User 2',2),('List 1 User 3',3),('List 1 User 3',3);


DROP TABLE IF EXISTS `products`;
CREATE TABLE products (
  id INT AUTO_INCREMENT NOT NULL,
   amount INT NOT NULL,
   product_name VARCHAR(255) NULL,
   list_id INT NULL,
   CONSTRAINT pk_product PRIMARY KEY (id)
);

INSERT INTO `products` (amount,product_name,list_id)
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


ALTER TABLE users ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE lists ADD CONSTRAINT FK_LIST_ON_LIST FOREIGN KEY (list_id) REFERENCES users (id);
ALTER TABLE authorities ADD CONSTRAINT FK_AUTHORITIES_ON_USERNAME FOREIGN KEY (userName_id) REFERENCES users (id);

ALTER TABLE products ADD CONSTRAINT FK_PRODUCT_ON_PRODUCT FOREIGN KEY (list_id) REFERENCES lists (id);
