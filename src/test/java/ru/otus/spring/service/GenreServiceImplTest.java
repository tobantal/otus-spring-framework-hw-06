package ru.otus.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

@SpringBootTest
public class GenreServiceImplTest {

	private GenreDao genreDao;
	private GenreService genreService;

	@Before
	public void setup() {
		genreDao = mock(GenreDao.class);
		given(genreDao.getAll()).willReturn(Collections.singletonList(new Genre(3, "comics")));
		genreService = new GenreServiceImpl(genreDao);
	}
	
	@Test
	public void shouldNotCallDaoIfItIsNotNecessary() {
		Genre genre = genreService.createIfItIsNecessaryAndGet("comics");
		verify(genreDao, times(0)).getByName(any());
		assertEquals(genre.getId(), 3);
	}
	
	@Test
	public void shouldDeleteById() {
		genreService.deleteById(1);;
		verify(genreDao, times(1)).deleteById(1);
	}

}
