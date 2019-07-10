package ru.otus.spring.dao;

import java.util.List;

import ru.otus.spring.domain.Book;

public interface BookDao extends Dao<Book> {
	
	List<Book> getAllByAuthor(String author);
	
	List<Book> getAllByGenre(String genre);
	
}
