DROP TABLE IF EXISTS genres; 

CREATE TABLE genres 
  ( 
     id   BIGINT NOT NULL auto_increment, 
     name VARCHAR(60) NOT NULL, 
     PRIMARY KEY(id) 
  ); 

DROP TABLE IF EXISTS authors; 

CREATE TABLE authors 
  ( 
     id   BIGINT NOT NULL auto_increment, 
     name VARCHAR(60) NOT NULL, 
     PRIMARY KEY(id) 
  ); 

DROP TABLE IF EXISTS books; 

CREATE TABLE books 
  ( 
     id        BIGINT NOT NULL auto_increment, 
     name      VARCHAR(60), 
     genre_id  BIGINT NOT NULL, 
     author_id BIGINT NOT NULL, 
     PRIMARY KEY(id), 
     FOREIGN KEY (genre_id) REFERENCES genres(id), 
     FOREIGN KEY (author_id) REFERENCES authors(id) 
  ); 