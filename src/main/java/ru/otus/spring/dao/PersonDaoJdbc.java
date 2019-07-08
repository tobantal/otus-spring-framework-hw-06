package ru.otus.spring.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Person;
import ru.otus.spring.mapper.PersonMapper;

@Repository
@RequiredArgsConstructor
public class PersonDaoJdbc implements PersonDao {

	private final NamedParameterJdbcOperations jdbc;
	private final PersonMapper personMapper;

	@Override
	public int count() {
		return jdbc.getJdbcOperations().queryForObject("select count(*) from persons", Integer.class);
	}

	@Override
	public void insert(Person person) {
		jdbc.getJdbcOperations().update("insert into persons (id, name) values (?, ?)", person.getId(), person.getName());
	}

	@Override
	public Person getById(int id) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("id", id);
		return jdbc.queryForObject("select * from persons where id = :id", params, personMapper);
	}

	@Override
	public List<Person> getAll() {
		return jdbc.query("select * from persons", personMapper);
	}

	@Override
	public void deleteById(int id) {
		final HashMap<String, Object> params = new HashMap<>(1);
		params.put("id", id);
		jdbc.update("delete from persons where id = :id", params);
	}

	@Override
	public Person getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
