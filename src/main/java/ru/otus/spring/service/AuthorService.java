package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

public interface AuthorService {
	
	Author getAuthorByName(String genre);
	
}
