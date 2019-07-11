package ru.otus.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;

import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;
//import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest
//@DisplayName("GenreServiceImpl должен ")
public class GenreServiceImplTest {

	//@Mock
	private GenreDao genreDao;

	private GenreService genreService;
	
	// сюдя явно задать поле ConcurrentMap из книги Spring5

	@Before
	public void setup() {
		genreDao = mock(GenreDao.class);
		given(genreDao.getAll()).willReturn(Collections.singletonList(new Genre(3, "comics")));
		genreService = new GenreServiceImpl(genreDao);
	}
	
	@Test
	//@DisplayName("не вызывать Dao, если есть жанр в кэше")
	public void shouldNotCallDaoIfItIsNotNecessary() {
		Genre genre = genreService.createIfItIsNecessaryAndGet("comics");
		verify(genreDao, times(0)).getByName(any());
		assertEquals(genre.getId(), 3);
	}
	
	/*
	@Test
	public void createIfItIsNecessaryAndGet_shouldCallDao() {
		Genre genre = genreService.createIfItIsNecessaryAndGet("fantasy");
		//verify(genreDao, times(1)).insert(any());
		//verify(genreDao, times(1)).getByName("fantasy");
		//assertNotEquals(genre.getId(), 3);
	}
	*/
	

}
