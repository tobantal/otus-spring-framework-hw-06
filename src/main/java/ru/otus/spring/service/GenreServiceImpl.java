package ru.otus.spring.service;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

@Service
public class GenreServiceImpl implements GenreService {
	
	private final GenreDao genreDao;
	private ConcurrentMap<String, Genre> genres;
	
	public GenreServiceImpl(GenreDao genreDao) {
		super();
		this.genreDao = genreDao;
		genres = genreDao.getAll().stream().collect(Collectors.toConcurrentMap(Genre::getName, g->g));
	}

	@Override
	public Genre createIfItIsNecessaryAndGet(String genre) {
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
	public int size() {
		return genreDao.count();
	}

	@Override
	public void deleteById(int id) {
		genreDao.deleteById(id);
		genres = genreDao.getAll().stream().collect(Collectors.toConcurrentMap(Genre::getName, g->g));
	}

	@Override
	public List<Genre> findAll() {
		return genreDao.getAll();
	}

}
