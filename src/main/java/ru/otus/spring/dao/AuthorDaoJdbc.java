package ru.otus.spring.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Author;
import ru.otus.spring.mapper.AuthorMapper;

@Repository
public class AuthorDaoJdbc extends JdbcDaoSupport implements AuthorDao {

	private final AuthorMapper mapper;
	
	public AuthorDaoJdbc(AuthorMapper mapper, JdbcTemplate jdbcTemplate) {
		this.mapper = mapper;
		super.setJdbcTemplate(jdbcTemplate);
	}

	@Override
	public int count() {
		return getJdbcTemplate().queryForObject("select count(*) from authors", Integer.class);
	}

	@Override
	public void insert(Author author) {
		getJdbcTemplate().update("insert into authors (id, name) values (?, ?)", author.getId(), author.getName());
	}

	@Override
	public Author getById(int id) {
		return getJdbcTemplate().queryForObject("select * from authors where id = " + id, mapper);
	}

	@Override
	public List<Author> getAll() {
		return getJdbcTemplate().query("select * from authors", mapper);
	}

	@Override
	public void deleteById(int id) {
		getJdbcTemplate().update("delete from authors where id = ?", id);
	}

	@Override
	public Author getByName(String name) {
		return getJdbcTemplate().queryForObject(String.format("select * from authors where name = '%s'", name), mapper);
	}


}
