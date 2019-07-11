package ru.otus.spring.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.domain.Genre;
import ru.otus.spring.mapper.GenreMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
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
	}

	@Test
	public void getById() {
		Genre genre = genreDao.getById(2);
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
		genreDao.deleteById(4);
		assertEquals(countBefore - 1, genreDao.count());
	}
	
	@Test
	public void getByName() {
		String name = "horrors";
		Genre genre = genreDao.getByName(name);
		assertNotNull(genre);
		assertEquals("horrors", genre.getName());
	}
}