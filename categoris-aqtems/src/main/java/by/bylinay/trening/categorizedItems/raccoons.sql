CREATE SCHEMA raccoons ;
USE raccoons;
CREATE TABLE  european  
( 
id INT PRIMARY KEY NOT NULL,
name_ VARCHAR(45) NOT NULL,
old INT NOT NULL,
date_ DATE NOT NULL
); 
CREATE TABLE  north_american
( 
id INT PRIMARY KEY NOT NULL,
name_ VARCHAR(45) NOT NULL,
old INT NOT NULL,
date_ DATE NOT NULL
);

INSERT INTO european
(id, name_, old, date_) 
 VALUES 

(212, 'king', 3 , '2008-10-23'),
(213,  'worior',6, '2009-11-13'),
(214,'metal', 5, '2011-08-09'),
(215,'witch', 2, '2008-10-04'),
(216,  'pleer',6, '2011-10-23'),
(217,'wit', 6, '2008-09-02');

INSERT INTO north_american
(id, name_, old, date_) 
 VALUES 

(312, 'viking', 6 , '2008-10-20'),
(313,  'gladiator',9, '2009-11-03'),
(314,'boss', 22, '2011-08-10'),
(315,'confederat', 11, '2008-10-11'),
(316,'witcher', 6, '2008-10-09');