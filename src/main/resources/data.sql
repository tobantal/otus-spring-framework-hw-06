INSERT INTO persons 
            (id, 
             name) 
VALUES      (1, 
             'masha'); 

INSERT INTO genres 
            (genre) 
VALUES      ('comics'), 
            ('horrors'), 
            ('fantasy'); 

INSERT INTO authors 
            (author) 
VALUES      ('Ivanov'), 
            ('Petrov'), 
            ('Sidorov'); 

INSERT INTO books 
            (genre_id, 
             author_id, 
             name) 
VALUES      (1, 
             2, 
             'Desert rose'), 
            (2, 
             1, 
             'Fly N2'), 
            (3, 
             3, 
             'Young Pirate'); 

INSERT INTO books_genres 
            (book_id, 
             genre_id) 
VALUES      (1, 
             2), 
            (2, 
             3); 

INSERT INTO books_authors 
            (book_id, 
             author_id) 
VALUES      (1, 
             3), 
            (2, 
             1); 