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
('cat',7,'2021-04-22'),('bear',9,'2021-04-22'),('raccoon',4,'2021-04-22'),('leshii',4,'2021-04-22'),('giraffe',5,'2021-04-22'),('panda',8,'2021-04-22'),('tiger',2,'2021-04-22');