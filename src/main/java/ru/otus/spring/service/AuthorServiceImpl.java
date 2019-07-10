package ru.otus.spring.service;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService, InitializingBean {
	
	private final AuthorDao authorDao;
	
	private ConcurrentMap<String, Author> authors;

	@Override
	public Author createIfItIsNecessaryAndGet(String author) {
		Author a;
		if(authors.containsKey(author)) {
			a = authors.get(author);
		} else {
			authorDao.insert(new Author(author));
			a = authorDao.getByName(author);
			authors.put(author, a);
		}
		return a;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		authors = authorDao.getAll().stream().collect(Collectors.toConcurrentMap(Author::getName, a->a));
	}

	@Override
	public int size() {
		return authorDao.count();
	}

	@Override
	public void deleteById(int id) {
		authorDao.deleteById(id);
		authors = authorDao.getAll().stream().collect(Collectors.toConcurrentMap(Author::getName, a->a));
	}

	@Override
	public List<Author> findAll() {
		return authorDao.getAll();
	}

}
