package ru.otus.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

@SpringBootTest
public class GenreServiceImplTest {

	@MockBean
	private GenreDao genreDao;
	
	@Autowired
	private GenreService genreService;
	
	@Test
	public void shouldGetByNameIfExists() {
		given(genreDao.getByName("comics")).willReturn(new Genre(3L, "comics"));
		genreService.createIfItIsNecessaryAndGet("comics");
		verify(genreDao, times(1)).getByName(any());
		verify(genreDao, times(0)).insert(any());
	}

	@Test
	public void shouldDeleteById() {
		genreService.deleteById(1L);
		verify(genreDao, times(1)).deleteById(1L);
	}
	
	@Test
	public void shouldFindAll() {
		given(genreDao.getAll()).willReturn(Collections.singletonList(new Genre(3L, "comics")));
		List<Genre> genres =  genreService.findAll();
		verify(genreDao, times(1)).getAll();
		assertThat(genres).hasSize(1);
		assertThat(genres.get(0).getId()).isEqualTo(3L);
		assertThat(genres.get(0).getName()).isEqualTo("comics");
	}

}
