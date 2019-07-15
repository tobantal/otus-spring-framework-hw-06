package ru.otus.spring.dao;

import java.util.List;

import ru.otus.spring.domain.Author;

public interface AuthorDao {
	
	int count();
	
	void insert(Author author);
	
	Author getById(Long id);
	
	List<Author> getAll();
	
	void deleteById(Long id);

	Author getByName(String name);
	
}
