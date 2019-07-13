package ru.otus.spring.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
	
	private final AuthorDao authorDao;

	@Override
	public Author createIfItIsNecessaryAndGet(String author) {		
		try {
			return authorDao.getByName(author);
		} catch(EmptyResultDataAccessException e) {
			authorDao.insert(new Author(author));
			return authorDao.getByName(author);
		}
	}

	@Override
	public int size() {
		return authorDao.count();
	}

	@Override
	public void deleteById(Long id) {
		authorDao.deleteById(id);
	}

	@Override
	public List<Author> findAll() {
		return authorDao.getAll();
	}

}
