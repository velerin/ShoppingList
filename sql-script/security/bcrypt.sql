DROP DATABASE  IF EXISTS `bcrypt_pass_encr`;

CREATE DATABASE  IF NOT EXISTS `bcrypt_pass_encr`;
USE `bcrypt_pass_encr`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16;


INSERT INTO `users`
VALUES
('john','{bcrypt}$2a$12$0xt5bl4kdxGfHi/9bR42bOqEE0leJMidk21hBLOwWq/NqK4Y2nmUe',1),
('mary','{bcrypt}$2a$12$0xt5bl4kdxGfHi/9bR42bOqEE0leJMidk21hBLOwWq/NqK4Y2nmUe',1),
('susan','{bcrypt}$2a$12$0xt5bl4kdxGfHi/9bR42bOqEE0leJMidk21hBLOwWq/NqK4Y2nmUe',1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_ADMIN');


