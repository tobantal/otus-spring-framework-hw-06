package ru.otus.spring.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.mapper.GenreMapper;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

	private final NamedParameterJdbcOperations jdbc;
	private final GenreMapper genreMapper;

	@Override
	public int count() {
		return jdbc.getJdbcOperations().queryForObject("select count(*) from genres", Integer.class);
	}

	@Override
	public void insert(Genre genre) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("id", genre.getId());
		params.put("name", genre.getName());
		jdbc.update("insert into genres (id, name) values (:id, :name)", params);
	}

	@Override
	public Genre getById(int id) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("id", id);
		return jdbc.queryForObject("select * from genres where id = :id", params, genreMapper);
	}

	@Override
	public List<Genre> getAll() {
		return jdbc.query("select * from genres", genreMapper);
	}

	@Override
	public void deleteById(int id) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("id", id);
		jdbc.update("delete from genres where id = :id", params);
	}

	@Override
	public Genre getByName(String name) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("name", name);
		return jdbc.queryForObject("select * from genres where name = :name", params, genreMapper);
	}
}