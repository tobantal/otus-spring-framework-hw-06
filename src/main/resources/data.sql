insert into persons (id, `name`) values (1, 'masha');


insert into genres (genre) values ('Комиксы'), ('Ужасы'), ('Фантастика');

insert into authors (author) values ('Иванов'), ('Петров'), ('Сидоров');

insert into books (name) values ('Роза ветров'), ('Полет №2'), ('Пират');

insert into books_genres (book_id, genre_id) values (1, 2), (2, 3);

insert into books_authors (book_id, author_id) values (1, 3), (2, 1);