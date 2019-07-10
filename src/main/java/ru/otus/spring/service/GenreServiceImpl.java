package ru.otus.spring.service;

import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService, InitializingBean {
	
	private final GenreDao genreDao;
	private ConcurrentMap<String, Genre> genres;

	@Override
	public Genre getGenreByName(String genre) {
		Genre g;
		if(genres.containsKey(genre)) {
			g = genres.get(genre);
		} else {
			genreDao.insert(new Genre(genre));
			g = genreDao.getByName(genre);
			genres.put(genre, g);
		}
		return g;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		genres = genreDao.getAll().stream().collect(Collectors.toConcurrentMap(Genre::getName, g->g));
	}

	@Override
	public int size() {
		return genreDao.count();
	}

	@Override
	public void deleteById(int id) {
		genreDao.deleteById(id);		
	}

}
