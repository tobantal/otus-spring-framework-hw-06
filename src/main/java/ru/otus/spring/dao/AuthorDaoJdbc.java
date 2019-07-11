package ru.otus.spring.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Author;
import ru.otus.spring.mapper.AuthorMapper;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao { 

	private final AuthorMapper mapper;
	private final JdbcOperations jdbc;

	@Override
	public int count() {
		return jdbc.queryForObject("select count(*) from authors", Integer.class);
	}

	@Override
	public void insert(Author author) {
		jdbc.update("insert into authors (id, name) values (?, ?)", author.getId(), author.getName());
	}

	@Override
	public Author getById(int id) {
		return jdbc.queryForObject("select * from authors where id = " + id, mapper);
	}

	@Override
	public List<Author> getAll() {
		return jdbc.query("select * from authors", mapper);
	}

	@Override
	public void deleteById(int id) {
		jdbc.update("delete from authors where id = ?", id);
	}

	@Override
	public Author getByName(String name) {
		return jdbc.queryForObject(String.format("select * from authors where name = '%s'", name), mapper);
	}


}
