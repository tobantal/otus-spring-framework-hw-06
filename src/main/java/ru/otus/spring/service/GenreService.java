package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

public interface GenreService {
	
	Genre getGenreByName(String genre);
	
	int size();
	
	void deleteById(int id);
	
}
