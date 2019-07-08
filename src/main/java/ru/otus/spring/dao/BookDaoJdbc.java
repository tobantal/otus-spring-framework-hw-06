package ru.otus.spring.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Book;
import ru.otus.spring.mapper.BookMapper;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

	private final NamedParameterJdbcOperations jdbc;
	private final BookMapper bookMapper;

	@Override
	public int count() {
		return jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
	}

	@Override
	public void insert(Book book) {
		jdbc.getJdbcOperations().update("insert into books (id, name) values (?, ?)", book.getId(), book.getName());
	}

	@Override
	public Book getById(int id) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("id", id);
		return jdbc.queryForObject("select * from books where id = :id", params, bookMapper);
	}

	@Override
	public List<Book> getAll() {
		return jdbc.query("select * from books", bookMapper);
	}

	@Override
	public void deleteById(int id) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("id", id);
		jdbc.update("delete from persons where id = :id", params);
	}
}
