DROP TABLE IF EXISTS persons; 

CREATE TABLE persons 
  ( 
     id   INT PRIMARY KEY, 
     name VARCHAR(255) 
  ); 

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
     FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE RESTRICT, 
     FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE RESTRICT 
  ); 

DROP TABLE IF EXISTS books_genres; 

CREATE TABLE books_genres 
  ( 
     id       INT(11) NOT NULL auto_increment, 
     book_id  INT(11), 
     genre_id INT(11), 
     PRIMARY KEY(id), 
     FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE RESTRICT, 
     FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE 
  ); 

DROP TABLE IF EXISTS books_authors; 

CREATE TABLE books_authors 
  ( 
     id        INT(11) NOT NULL auto_increment, 
     book_id   INT(11), 
     author_id INT(11), 
     PRIMARY KEY(id), 
     FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE RESTRICT, 
     FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE 
  ); 