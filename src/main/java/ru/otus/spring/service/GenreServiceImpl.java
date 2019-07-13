package ru.otus.spring.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

	private final GenreDao genreDao;

	@Override
	public Genre createIfItIsNecessaryAndGet(String genre) {
		Genre g;
		try {
			g = genreDao.getByName(genre);
			return g;			
		} catch(EmptyResultDataAccessException e) {
			genreDao.insert(new Genre(genre));
			g = genreDao.getByName(genre);
			return g;
		}
		
	}

	@Override
	public int size() {
		return genreDao.count();
	}

	@Override
	public void deleteById(Long id) {
		genreDao.deleteById(id);
	}

	@Override
	public List<Genre> findAll() {
		return genreDao.getAll();
	}

}
