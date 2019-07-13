package ru.otus.spring.service;

import java.util.List;

import ru.otus.spring.domain.Genre;

public interface GenreService {
	
	Genre createIfItIsNecessaryAndGet(String genre);
	
	int size();
	
	void deleteById(Long id);
	
	List<Genre> findAll();
	
}
