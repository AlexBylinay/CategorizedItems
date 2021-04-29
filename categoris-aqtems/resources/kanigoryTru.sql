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

INSERT INTO item
( name_, category_id, date_) 
 VALUES 

( 'king', 3,  '2008-10-23'),
( 'worior',2,  '2009-11-13'),
('menal',  1, '2011-08-09'),
('witch', 4, '2008-10-04'),
( 'wolf', 5, '2014-12-10');

INSERT INTO category
( name_, color, date_) 
 VALUES 

( 'cat',  2, '2007-10-23'),
( 'raccoon',3, '2009-10-13'),
('dog',  9, '2012-07-09'),
('badger', 3, '2009-10-04'),
( 'panda', 5, '2014-02-11');

