package ru.otus.spring.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.domain.Genre;
import ru.otus.spring.mapper.GenreMapper;

@JdbcTest
@Import({ GenreDaoJdbc.class, GenreMapper.class })
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class GenreDaoJdbcTest {

	@Autowired
	GenreDao genreDao;

	@Test
	public void insert() {
		Genre genre = new Genre("humor");
		int countBefore = genreDao.count();
		genreDao.insert(genre);
		assertEquals(countBefore + 1, genreDao.count());
		assertNotNull(genreDao.getByName("humor"));
	}

	@Test
	public void getById() {
		Genre genre = genreDao.getById(2L);
		assertNotNull(genre);
		assertEquals("horrors", genre.getName());
	}
	
	@Test
	public void getAll() {
		List<Genre> genres = genreDao.getAll();
		int count = genreDao.count();
		assertEquals(count, genres.size());
	}
	
	@Test
	public void deleteById() {
		int countBefore = genreDao.count();
		genreDao.deleteById(4L);
		assertEquals(countBefore - 1, genreDao.count());
		assertThrows(EmptyResultDataAccessException.class, () -> genreDao.getById(4L));
	}
	
	@Test
	public void getByName() {
		String name = "horrors";
		Genre genre = genreDao.getByName(name);
		assertNotNull(genre);
		assertEquals("horrors", genre.getName());
	}
}