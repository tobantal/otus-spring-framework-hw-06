package ru.otus.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.otus.spring.domain.Book;;

@Component
public class BookMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		final int id = rs.getInt("id");
		final String name = rs.getString("name");
		final int author_id = rs.getInt("author_id");
		final int genre_id = rs.getInt("genre_id");
		return new Book(id, name, author_id, genre_id);
	}

}
