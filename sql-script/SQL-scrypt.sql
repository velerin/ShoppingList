DROP DATABASE  IF EXISTS `web-shopping-list`;

CREATE DATABASE  IF NOT EXISTS `web-shopping-list`;
USE `web-shopping-list`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE user (
  id INT AUTO_INCREMENT NOT NULL,
   first_name VARCHAR(255) NULL,
   last_name VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   user_name VARCHAR(255) NULL,
   password VARCHAR(255) NULL,
   CONSTRAINT pk_user PRIMARY KEY (id)
);

INSERT INTO `user` (user_name,password,first_name,last_name,email)
VALUES 
('john','{bcrypt}$2a$12$TCQZHxH4oWniBX1nDDYere9UszxzrvBkyeQSnIYE158jQuQ7X2uUO','John','Doe','john@luv2code.com'),
('mary','{bcrypt}$2a$12$TCQZHxH4oWniBX1nDDYere9UszxzrvBkyeQSnIYE158jQuQ7X2uUO','Mary','Public','mary@luv2code.com'),
('susan','{bcrypt}$2a$12$TCQZHxH4oWniBX1nDDYere9UszxzrvBkyeQSnIYE158jQuQ7X2uUO','Susan','Adams','susan@luv2code.com');

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE users_roles (
  role_id INT NOT NULL,
   user_id INT NOT NULL
);

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3);

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  id INT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   CONSTRAINT pk_role PRIMARY KEY (id)
);

INSERT INTO `role` (name)
VALUES 
('ROLE_EMPLOYEE'),('ROLE_MANAGER'),('ROLE_ADMIN');

DROP TABLE IF EXISTS `list`;
CREATE TABLE list (
  id INT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NULL,
   list_id INT NULL,
   CONSTRAINT pk_list PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `product`;
CREATE TABLE product (
  id INT AUTO_INCREMENT NOT NULL,
   amount INT NOT NULL,
   product_name VARCHAR(255) NULL,
   product_id INT NULL,
   CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE user ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE users_roles ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE users_roles ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE list ADD CONSTRAINT FK_LIST_ON_LIST FOREIGN KEY (list_id) REFERENCES user (id);

ALTER TABLE product ADD CONSTRAINT FK_PRODUCT_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES list (id);
