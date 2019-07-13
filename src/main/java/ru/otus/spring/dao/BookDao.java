package ru.otus.spring.dao;

import java.util.List;

import ru.otus.spring.domain.Book;

public interface BookDao {
	
	int count();
	
	void insert(Book book);
	
	Book getById(Long id);
	
	List<Book> getAll();
	
	void deleteById(Long id);

	Book getByName(String name);
	
	List<Book> getAllByAuthor(String author);
	
	List<Book> getAllByGenre(String genre);
	
}
