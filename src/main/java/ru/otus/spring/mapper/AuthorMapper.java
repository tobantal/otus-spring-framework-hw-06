package ru.otus.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.otus.spring.domain.Author;

@Component
public class AuthorMapper implements RowMapper<Author> {

	@Override
	public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
		final int id = rs.getInt("id");
		final String name = rs.getString("name");
		return new Author(id, name);
	}

}
