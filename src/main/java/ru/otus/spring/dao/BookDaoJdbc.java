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
@SuppressWarnings("serial")
public class BookDaoJdbc implements BookDao {

	private static final String COUNT_SQL_QUERY = "select count(*) from books";
	private static final String FIND_ALL_SQL_QUERY = "	SELECT books.id, \r\n" + "		       books.NAME, \r\n"
			+ "		       author_id, \r\n" + "		       authors.NAME AS author, \r\n"
			+ "		       genre_id, \r\n" + "		       genres.NAME AS genre \r\n" + "			FROM   ((books \r\n"
			+ "		    INNER JOIN authors \r\n" + "		                 ON books.author_id = authors.id) \r\n"
			+ "		    INNER JOIN genres \r\n" + "		                ON books.genre_id = genres.id )";

	private static final String FIND_BY_NAME_SQL_QUERY = FIND_ALL_SQL_QUERY + " WHERE books.name = :name";
	private static final String FIND_BY_AUTHOR_SQL_QUERY = FIND_ALL_SQL_QUERY + " WHERE authors.name = :name";
	private static final String FIND_BY_GENRE_SQL_QUERY = FIND_ALL_SQL_QUERY + " WHERE genres.name = :name";
	private static final String FIND_BY_ID_SQL_QUERY = FIND_ALL_SQL_QUERY + " WHERE books.id = :id";
	private static final String INSERT_BOOK_SQL_QUERY = "insert into books (name, author_id, genre_id) values (:name, :author_id, :genre_id)";
	private static final String DELETE_BOOK_SQL_QUERY = "delete from books where id = :id";

	private final NamedParameterJdbcOperations jdbc;
	private final BookMapper bookMapper;

	@Override
	public int count() {
		return jdbc.getJdbcOperations().queryForObject(COUNT_SQL_QUERY, Integer.class);
	}

	@Override
	public void insert(Book book) {
		jdbc.update(INSERT_BOOK_SQL_QUERY, new HashMap<String, Object>(4) {
			{
				put("name", book.getName());
				put("author_id", book.getAuthor().getId());
				put("genre_id", book.getGenre().getId());
			}
		});
	}

	@Override
	public Book getById(int id) {
		return jdbc.queryForObject(FIND_BY_ID_SQL_QUERY, new HashMap<String, Object>(1) {
			{
				put("id", id);
			}
		}, bookMapper);
	}

	@Override
	public List<Book> getAll() {
		return jdbc.query(FIND_ALL_SQL_QUERY, bookMapper);
	}

	@Override
	public void deleteById(int id) {
		jdbc.update(DELETE_BOOK_SQL_QUERY, new HashMap<String, Object>(1) {
			{
				put("id", id);
			}
		});
	}

	@Override
	public Book getByName(String name) {
		return jdbc.queryForObject(FIND_BY_NAME_SQL_QUERY, new HashMap<String, Object>(1) {
			{
				put("name", name);
			}
		}, bookMapper);
	}

	@Override
	public List<Book> getAllByAuthor(String author) {
		return jdbc.query(FIND_BY_AUTHOR_SQL_QUERY, new HashMap<String, Object>(1) {
			{
				put("name", author);
			}
		}, bookMapper);
	}

	@Override
	public List<Book> getAllByGenre(String genre) {
		return jdbc.query(FIND_BY_GENRE_SQL_QUERY, new HashMap<String, Object>(1) {
			{
				put("name", genre);
			}
		}, bookMapper);
	}
}
