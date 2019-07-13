package ru.otus.spring.dao;

import java.util.List;

import ru.otus.spring.domain.Genre;

public interface GenreDao {
	
	int count();
	
	void insert(Genre genre);
	
	Genre getById(int id);
	
	List<Genre> getAll();
	
	void deleteById(int id);

	Genre getByName(String name);
	
}
