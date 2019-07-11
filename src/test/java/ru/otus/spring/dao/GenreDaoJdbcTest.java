package ru.otus.spring.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.mapper.GenreMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@JdbcTest
@Import({GenreDaoJdbc.class, GenreMapper.class})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class GenreDaoJdbcTest {
    
    @Autowired 
    GenreDao genreDao;

    @Test
    public void checkCount(){
       assertNotNull(genreDao);
       assertEquals(4, genreDao.count());
    }
}