package ru.otus.spring.service;

import java.util.List;

import ru.otus.spring.domain.Book;

public interface BookService {
	
	int size();
	
	Book findBookByName(String name);
	
	void addBook(String name, String author, String genre);
	
	void deleteBookById(int id);
	
	List<Book> findAllBooks();
	
	List<Book> findBooksByAuthor(String author);
	
	List<Book> findBooksByGenre(String genre);

}
