package ru.otus.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Person;;

@Component
public class BookMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		final int id = rs.getInt("id");
		final String name = rs.getString("name");
		final String author = rs.getString("author");
		final String genre = rs.getString("genre");
		return new Book(id, name, author, genre);
	}

}
