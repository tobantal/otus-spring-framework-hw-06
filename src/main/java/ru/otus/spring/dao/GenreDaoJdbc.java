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
@SuppressWarnings("serial")
public class GenreDaoJdbc implements GenreDao {

	private final NamedParameterJdbcOperations jdbc;
	private final GenreMapper genreMapper;

	@Override
	public int count() {
		return jdbc.getJdbcOperations().queryForObject("select count(*) from genres", Integer.class);
	}

	@Override
	public void insert(Genre genre) {
		jdbc.update("insert into genres (id, name) values (:id, :name)", new HashMap<String, Object>(2) {
			{
				put("id", genre.getId());
				put("name", genre.getName());
			}
		});
	}

	@Override
	public Genre getById(int id) {
		return jdbc.queryForObject("select * from genres where id = :id", new HashMap<String, Object>(1) {
			{
				put("id", id);
			}
		}, genreMapper);
	}

	@Override
	public List<Genre> getAll() {
		return jdbc.query("select * from genres", genreMapper);
	}

	@Override
	public void deleteById(int id) {
		jdbc.update("delete from genres where id = :id", new HashMap<String, Object>(1) {
			{
				put("id", id);
			}
		});
	}

	@Override
	public Genre getByName(String name) {
		return jdbc.queryForObject("select * from genres where name = :name", new HashMap<String, Object>(1) {
			{
				put("name", name);
			}
		}, genreMapper);
	}
}