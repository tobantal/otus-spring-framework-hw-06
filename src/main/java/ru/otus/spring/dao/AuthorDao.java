package ru.otus.spring.dao;

import java.util.List;

import ru.otus.spring.domain.Author;

public interface AuthorDao {
	
	int count();
	
	void insert(Author author);
	
	Author getById(int id);
	
	List<Author> getAll();
	
	void deleteById(int id);

	Author getByName(String name);
	
}
