package ru.otus.spring.service;

import java.util.List;

import ru.otus.spring.domain.Author;

public interface AuthorService {
	
	Author createIfItIsNecessaryAndGet(String author);
	
	int size();
	
	void deleteById(int id);
	
	List<Author> findAll();
	
}
