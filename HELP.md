SELECT name as "Имя", genre as "Жанр", author as "Автор"
FROM books, books_genres, genres, authors, books_authors
WHERE books.id= books_genres.book_id AND books_genres.genre_id = genres.id AND books.id= books_authors.book_id AND books_authors.author_id = authors.id;
