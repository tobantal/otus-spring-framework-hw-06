package ru.otus.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
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
		genreService = new GenreServiceImpl(genreDao);
	}
	
	@Test
	public void shouldNotCallDaoIfItIsNotNecessary() {
		Genre genre = genreService.createIfItIsNecessaryAndGet("comics");
		verify(genreDao, times(0)).getByName(any());
		assertThat(genre.getId()).isEqualTo(3L);
	}
	
	@Test
	public void shouldDeleteById() {
		genreService.deleteById(1L);
		verify(genreDao, times(1)).deleteById(1L);
	}

}
