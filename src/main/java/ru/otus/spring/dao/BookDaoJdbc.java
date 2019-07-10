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

	private static final String FIND_ALL_SQL_QUERY = "	SELECT books.id, \r\n" + 
			"		       books.NAME, \r\n" + 
			"		       author_id, \r\n" + 
			"		       authors.NAME AS author, \r\n" + 
			"		       genre_id, \r\n" + 
			"		       genres.NAME AS genre \r\n" + 
			"			FROM   ((books \r\n" + 
			"		    INNER JOIN authors \r\n" + 
			"		                 ON books.author_id = authors.id) \r\n" + 
			"		    INNER JOIN genres \r\n" + 
			"		                ON books.genre_id = genres.id )";
	
	private static final String FIND_BY_NAME_SQL_QUERY = FIND_ALL_SQL_QUERY + " WHERE books.name = :name";
	
	private final NamedParameterJdbcOperations jdbc;
	private final BookMapper bookMapper;

	@Override
	public int count() {
		return jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
	}

	@Override
	public void insert(Book book) {
		jdbc.getJdbcOperations().update("insert into books (name, author_id, genre_id) values (?, ?, ?)", book.getName(), book.getAuthor().getId(), book.getGenre().getId());
	}

	@Override
	public Book getById(int id) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("id", id);
		return jdbc.queryForObject("select * from books where id = :id", params, bookMapper);
	}

	@Override
	public List<Book> getAll() {
		return jdbc.query(FIND_ALL_SQL_QUERY, bookMapper);
	}

	@Override
	public void deleteById(int id) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("id", id);
		jdbc.update("delete from books where id = :id", params);
	}

	@Override
	public Book getByName(String name) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("name", name);
		return jdbc.queryForObject(FIND_BY_NAME_SQL_QUERY, params, bookMapper);
	}
}
