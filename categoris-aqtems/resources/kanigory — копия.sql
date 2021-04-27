DROP SCHEMA IF EXISTS categoriz;
CREATE SCHEMA categoriz;
USE categoriz;

CREATE TABLE category
(
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name_ VARCHAR(45) NOT NULL,
color INT NOT NULL,
date_ DATE NOT NULL

);

INSERT INTO category
( name_, color, date_) 
 VALUES 
('yeti',2,'2021-04-26'),('giraffe',9,'2021-04-26'),('bear',1,'2021-04-26'),('bear',5,'2021-04-26'),('bear',8,'2021-04-26'),('parrot',8,'2021-04-26'),('giraffe',4,'2021-04-26');