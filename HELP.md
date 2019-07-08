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

***********************

insert into persons (id, `name`) values (1, 'masha');


insert into genres (genre) values ('Комиксы'), ('Ужасы'), ('Фантастика');

insert into authors (author) values ('Иванов'), ('Петров'), ('Сидоров');

insert into books (name) values ('Роза ветров'), ('Полет №2'), ('Пират');

insert into books_genres (book_id, genre_id) values (1, 2), (2, 3);

insert into books_authors (book_id, author_id) values (1, 3), (2, 1);

***********************
SELECT name as "Имя", genre as "Жанр", author as "Автор"
FROM books, books_genres, genres, authors, books_authors
WHERE books.id= books_genres.book_id AND books_genres.genre_id = genres.id AND books.id= books_authors.book_id AND books_authors.author_id = authors.id;

-----------------------------
SELECT NAME, 
       author, 
       genre 
FROM   ((books 
         INNER JOIN authors 
                 ON books.author_id = authors.id) 
        INNER JOIN genres 
                ON books.genre_id = genres.id )
WHERE
       author LIKE '%rov';
-----------------------------

-----------------------------

-----------------------------