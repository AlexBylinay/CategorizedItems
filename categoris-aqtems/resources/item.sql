DROP SCHEMA IF EXISTS item;
CREATE SCHEMA item;
USE item;

CREATE TABLE items
( 
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name_ VARCHAR(45) NOT NULL,
category_id INT NOT NULL,
date_ DATE NOT NULL
); 
USE item;
INSERT INTO items ( name_, category_id, date_) VALUES( 'wolf', 5, '2014-12-10');;