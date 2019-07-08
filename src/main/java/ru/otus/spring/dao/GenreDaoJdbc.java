package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.domain.Person;
import ru.otus.spring.mapper.GenreMapper;
import ru.otus.spring.mapper.PersonMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
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
		jdbc.getJdbcOperations().update("insert into genres (id, name) values (?, ?)", genre.getId(), genre.getName());
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
		// TODO Auto-generated method stub
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("name", name);
		return jdbc.queryForObject("select * from genres where name = :name", params, genreMapper);
	}
}
