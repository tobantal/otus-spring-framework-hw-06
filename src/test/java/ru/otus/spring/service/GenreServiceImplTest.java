package ru.otus.spring.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

@SpringBootTest
public class GenreServiceImplTest {

	private GenreDao genreDao;
	private GenreService genreService;

	@BeforeEach
	public void setup() {
		genreDao = mock(GenreDao.class);
		given(genreDao.getAll()).willReturn(Collections.singletonList(new Genre(3L, "comics")));
		given(genreDao.getByName("comics")).willReturn(new Genre(3L, "comics"));
		genreService = new GenreServiceImpl(genreDao);
	}
	
	@Test
	public void shouldGetByNameIfExists() {
		genreService.createIfItIsNecessaryAndGet("comics");
		verify(genreDao, times(1)).getByName(any());
		verify(genreDao, times(0)).insert(any());
	}

	@Test
	public void shouldDeleteById() {
		genreService.deleteById(1L);
		verify(genreDao, times(1)).deleteById(1L);
	}

}
