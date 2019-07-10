package ru.otus.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;;

@Component
public class BookMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		final int id = rs.getInt("id");
		final String name = rs.getString("name");
		final int author_id = rs.getInt("author_id");
		final String author = rs.getString("author");
		final int genre_id = rs.getInt("genre_id");
		final String genre = rs.getString("genre");
		return new Book(id, name, new Author(author_id, author), new Genre(genre_id, genre));
	}

}
