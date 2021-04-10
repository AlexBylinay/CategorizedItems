DROP SCHEMA IF EXISTS categorizedItemsTru;
CREATE SCHEMA categorizedItemsTru ;
USE categorizedItemsTru;

CREATE TABLE  item
( 
id INT PRIMARY KEY NOT NULL,
name_ VARCHAR(45) NOT NULL,
category_id INT NOT NULL,
date_ DATE NOT NULL
); 

CREATE TABLE category
(
id INT PRIMARY KEY NOT NULL,
name_ VARCHAR(45) NOT NULL,
color INT NOT NULL,
date_ DATE NOT NULL
);

INSERT INTO item
(id, name_, category_id, date_) 
 VALUES 

(212, 'king',  112, '2008-10-23'),
(213,  'worior', 113 , '2009-11-13'),
(214,'menal',  114, '2011-08-09'),
(215,'witch', 115, '2008-10-04'),
(216, 'wolf', 116, '2014-12-10');

INSERT INTO category
(id, name_, color, date_) 
 VALUES 

(112, 'cat',  2, '2007-10-23'),
(113,  'raccoon',3, '2009-10-13'),
(114,'dog',  9, '2012-07-09'),
(115,'badger', 3, '2009-10-04'),
(116, 'panda', 5, '2014-02-11');

