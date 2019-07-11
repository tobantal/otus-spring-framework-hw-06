package ru.otus.spring.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.mapper.AuthorMapper;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@JdbcTest
@Import({AuthorDaoJdbc.class, AuthorMapper.class})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AuthorDaoJdbcTest {
	
	@Autowired
	AuthorDao authorDao;

	@Test
	public void test() {
		assertNotNull(authorDao);
	}

}
