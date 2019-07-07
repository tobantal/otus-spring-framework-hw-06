package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Person;

import java.util.List;

public interface BookDao {
	
	int count();
	
	void insert(Book book);
	
	Person getById(int id);
	
	List<Book> getAll();
	
	void deleteById(int id);

}
