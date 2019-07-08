package ru.otus.spring.service;

import java.util.List;

import ru.otus.spring.dto.BookDto;

public interface BookService {
	
	int size();
	
	BookDto findBookByName(String name);
	
	void addBook(String name, String author, String genre);
	
	void deleteBookById(int id);
	
	List<BookDto> findAllBooks();

}
