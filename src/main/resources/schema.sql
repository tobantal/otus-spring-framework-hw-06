DROP TABLE IF EXISTS PERSONS;
CREATE TABLE PERSONS(ID INT PRIMARY KEY, NAME VARCHAR(255));


DROP TABLE IF EXISTS BOOKS;
CREATE TABLE books (id int(11) not null auto_increment, name varchar(60), primary key(id));

DROP TABLE IF EXISTS GENRES;
CREATE TABLE genres (id int(11) not null auto_increment, genre varchar(60), primary key(id));

DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE authors (id int(11) not null auto_increment, author varchar(60), primary key(id));

DROP TABLE IF EXISTS BOOKS_GENRES;
CREATE TABLE BOOKS_GENRES (id int(11) not null auto_increment, book_id int(11), genre_id int(11), primary key(id), FOREIGN KEY (genre_id)
 REFERENCES genres(id) ON DELETE RESTRICT, FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE);

DROP TABLE IF EXISTS BOOKS_AUTHORS;
CREATE TABLE BOOKS_AUTHORS (id int(11) not null auto_increment, book_id int(11), author_id int(11), primary key(id), FOREIGN KEY (author_id) 
REFERENCES authors(id) ON DELETE RESTRICT, FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE);