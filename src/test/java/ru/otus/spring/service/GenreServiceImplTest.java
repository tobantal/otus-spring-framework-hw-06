package ru.otus.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GenreServiceImplTest {

	private GenreDao genreDao;

	private GenreServiceImpl genreService;

	@BeforeEach
	public void setup() {
		genreDao = mock(GenreDao.class);
		given(genreDao.getAll()).willReturn(Collections.singletonList(new Genre(3, "comics")));
		genreService = new GenreServiceImpl(genreDao);
	}
	
	@Test
	void createIfItIsNecessaryAndGet_shouldNotCallDaoIfItIsNotNecessary() {
		Genre genre = genreService.createIfItIsNecessaryAndGet("comics");
		verify(genreDao, times(0)).getByName(any());
		assertEquals(genre.getId(), 3);
	}
	
	/*
	@Test
	void createIfItIsNecessaryAndGet_shouldCallDao() {
		Genre genre = genreService.createIfItIsNecessaryAndGet("fantasy");
		verify(genreDao, times(1)).insert(any());
		//verify(genreDao, times(1)).getByName("fantasy");
		//assertNotEquals(genre.getId(), 3);
	}
	*/

}
