package ru.otus.spring.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Author;
import ru.otus.spring.mapper.AuthorMapper;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

	private final NamedParameterJdbcOperations jdbc;
	private final AuthorMapper mapper;

	@Override
	public int count() {
		return jdbc.getJdbcOperations().queryForObject("select count(*) from authors", Integer.class);
	}

	@Override
	public void insert(Author author) {
		jdbc.getJdbcOperations().update("insert into authors (id, name) values (?, ?)", author.getId(), author.getName());
	}

	@Override
	public Author getById(int id) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("id", id);
		return jdbc.queryForObject("select * from authors where id = :id", params, mapper);
	}

	@Override
	public List<Author> getAll() {
		return jdbc.query("select * from authors", mapper);
	}

	@Override
	public void deleteById(int id) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("id", id);
		jdbc.update("delete from authors where id = :id", params);
	}

	@Override
	public Author getByName(String name) {
		// TODO Auto-generated method stub
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("name", name);
		return jdbc.queryForObject("select * from authors where name = :name", params, mapper);
	}
}
