DROP TABLE IF EXISTS genres; 

CREATE TABLE genres 
  ( 
     id    INT(11) NOT NULL auto_increment, 
     name VARCHAR(60) NOT NULL, 
     PRIMARY KEY(id) 
  ); 

DROP TABLE IF EXISTS authors; 

CREATE TABLE authors 
  ( 
     id     INT(11) NOT NULL auto_increment, 
     name VARCHAR(60) NOT NULL, 
     PRIMARY KEY(id) 
  ); 

DROP TABLE IF EXISTS books; 

CREATE TABLE books 
  ( 
     id        INT(11) NOT NULL auto_increment, 
     name      VARCHAR(60), 
     genre_id  INT(11) NOT NULL, 
     author_id INT(11) NOT NULL, 
     PRIMARY KEY(id), 
     FOREIGN KEY (genre_id) REFERENCES genres(id), 
     FOREIGN KEY (author_id) REFERENCES authors(id) 
  );