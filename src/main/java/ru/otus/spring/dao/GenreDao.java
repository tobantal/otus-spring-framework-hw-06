package ru.otus.spring.dao;

import java.util.List;

import ru.otus.spring.domain.Genre;

public interface GenreDao {
	
	int count();
	
	void insert(Genre genre);
	
	Genre getById(Long id);
	
	List<Genre> getAll();
	
	void deleteById(Long id);

	Genre getByName(String name);
	
}
