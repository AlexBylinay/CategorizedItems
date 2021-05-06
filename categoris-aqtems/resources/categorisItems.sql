DROP SCHEMA IF EXISTS categorizedItemsTru;
CREATE SCHEMA categorizedItemsTru ;
USE categorizedItemsTru;

CREATE TABLE  item
( 
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name_ VARCHAR(45) NOT NULL UNIQUE,
category_id INT NOT NULL,
date_ DATE NOT NULL
); 

CREATE TABLE category
(
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name_ VARCHAR(45) NOT NULL UNIQUE,
color INT NOT NULL,
date_ DATE NOT NULL

);


