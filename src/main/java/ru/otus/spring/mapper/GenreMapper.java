package ru.otus.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.otus.spring.domain.Genre;
import ru.otus.spring.domain.Person;;

@Component
public class GenreMapper implements RowMapper<Genre> {

	@Override
	public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
		final int id = rs.getInt("id");
		final String name = rs.getString("genre");
		return new Genre(id, name);
	}

}
