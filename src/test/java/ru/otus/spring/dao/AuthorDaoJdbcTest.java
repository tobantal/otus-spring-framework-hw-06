package ru.otus.spring.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.domain.Author;
import ru.otus.spring.mapper.AuthorMapper;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@JdbcTest
@Import({AuthorDaoJdbc.class, AuthorMapper.class})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AuthorDaoJdbcTest {
	
	@Autowired
	AuthorDao authorDao;

	@Test
	public void insert() {
		Author author = new Author("Pushkin");
		int countBefore = authorDao.count();
		authorDao.insert(author);
		assertEquals(countBefore + 1, authorDao.count());
	}

	@Test
	public void getById() {
		Author author = authorDao.getById(2);
		assertNotNull(author);
		assertEquals("Petrov", author.getName());
	}
	
	@Test
	public void getAll() {
		List<Author> authors = authorDao.getAll();
		int count = authorDao.count();
		assertEquals(count, authors.size());
	}
	
	@Test
	public void deleteById() {
		int countBefore = authorDao.count();
		authorDao.deleteById(4);
		assertEquals(countBefore - 1, authorDao.count());
	}
	
	@Test
	public void getByName() {
		String name = "Ivanov";
		Author author = authorDao.getByName(name);
		assertNotNull(author);
		assertEquals("Ivanov", author.getName());
	}
	
}
