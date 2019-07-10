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
		// find or insert into author
		// find or insert into genre
		jdbc.getJdbcOperations().update("insert into books (name, author_id, genre_id) values (?, ?, ?)", book.getName(), book.getAuthorId(), book.getGenreId());
	}

	@Override
	public Book getById(int id) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("id", id);
		return jdbc.queryForObject("select * from books where id = :id", params, bookMapper);
	}

	@Override
	public List<Book> getAll() {
		/*
		 // findAllBooks()
		 SELECT books.id, 
		       books.NAME, 
		       author_id, 
		       authors.NAME AS author, 
		       genre_id, 
		       genres.NAME  AS genre 
		FROM   ((books 
		         INNER JOIN authors 
		                 ON books.author_id = authors.id) 
		        INNER JOIN genres 
		                ON books.genre_id = genres.id );
		 
		*/
		return jdbc.query("select * from books", bookMapper);
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
		return jdbc.queryForObject("select * from books where name = :name", params, bookMapper);
	}
}
