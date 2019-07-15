package ru.otus.spring.dao;

import java.util.Collections;
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

	private final NamedParameterJdbcOperations jdbc;
	private final BookMapper bookMapper;

	@Override
	public int count() {
		return jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
	}

	@Override
	public void insert(Book book) {
		jdbc.update("insert into books (name, author_id, genre_id) values (:name, :author_id, :genre_id)",
				new HashMap<String, Object>(3) {
					{
						put("name", book.getName());
						put("author_id", book.getAuthor().getId());
						put("genre_id", book.getGenre().getId());
					}
				});
	}

	@Override
	public Book getById(Long id) {
		return jdbc.queryForObject(
				"SELECT books.id, books.NAME, author_id, authors.NAME AS author, genre_id, genres.NAME AS genre"
						+ " FROM ((books INNER JOIN authors ON books.author_id = authors.id) INNER JOIN genres ON books.genre_id = genres.id) WHERE books.id = :id",
				Collections.singletonMap("id", id), bookMapper);
	}

	@Override
	public List<Book> getAll() {
		return jdbc.query(
				"SELECT books.id, books.NAME, author_id, authors.NAME AS author, genre_id, genres.NAME AS genre"
						+ " FROM ((books INNER JOIN authors ON books.author_id = authors.id) INNER JOIN genres ON books.genre_id = genres.id )",
				bookMapper);
	}

	@Override
	public void deleteById(Long id) {
		jdbc.update("delete from books where id = :id", Collections.singletonMap("id", id));
	}

	@Override
	public Book getByName(String name) {
		return jdbc.queryForObject(
				"SELECT books.id, books.NAME, author_id, authors.NAME AS author, genre_id, genres.NAME AS genre"
						+ " FROM ((books INNER JOIN authors ON books.author_id = authors.id) INNER JOIN genres ON books.genre_id = genres.id ) WHERE books.name = :name",
				Collections.singletonMap("name", name), bookMapper);
	}

	@Override
	public List<Book> getAllByAuthor(String author) {
		return jdbc.query(
				"SELECT books.id, books.NAME, author_id, authors.NAME AS author, genre_id, genres.NAME AS genre"
						+ " FROM ((books INNER JOIN authors ON books.author_id = authors.id) INNER JOIN genres ON books.genre_id = genres.id) WHERE authors.name = :name",
				Collections.singletonMap("name", author), bookMapper);
	}

	@Override
	public List<Book> getAllByGenre(String genre) {
		return jdbc.query(
				"SELECT books.id, books.NAME, author_id, authors.NAME AS author, genre_id, genres.NAME AS genre"
						+ " FROM ((books INNER JOIN authors ON books.author_id = authors.id) INNER JOIN genres ON books.genre_id = genres.id ) WHERE genres.name = :name",
				Collections.singletonMap("name", genre), bookMapper);
	}
}
