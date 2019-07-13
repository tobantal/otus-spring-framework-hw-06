package ru.otus.spring.dao;

import java.util.List;

import ru.otus.spring.domain.Book;

public interface BookDao {
	
	int count();
	
	void insert(Book book);
	
	Book getById(int id);
	
	List<Book> getAll();
	
	void deleteById(int id);

	Book getByName(String name);
	
	List<Book> getAllByAuthor(String author);
	
	List<Book> getAllByGenre(String genre);
	
}
