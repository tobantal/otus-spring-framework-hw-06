package ru.otus.spring.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import ru.otus.spring.mapper.GenreMapper;


@DisplayName("GenreDaoJdbc должен ")
@JdbcTest
@Import({ApplicationContext.class, GenreDaoJdbc.class, NamedParameterJdbcOperations.class, GenreMapper.class})
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class GenreDaoJdbcTest {
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	NamedParameterJdbcOperations jdbc;
	
	@Autowired
	GenreMapper genreMapper;
	
	@Autowired
    GenreDaoJdbc genreDao;
	
	/*
	  @Autowired
    private OtusStudentRepositoryJdbcImpl repositoryJdbc;

    @DisplayName("должен загружать список всех студентов с полной информацией о них")
    @Test
    void shouldReturnCorrectStudentsListWithAllInfo() {
        val students = repositoryJdbc.findAllWithAllInfo();
        assertThat(students).isNotNull().hasSize(10).allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);

    }
	 */
	
	/*
@RunWith(SpringRunner.class)
@JdbcTest
@Import({PersonDaoJdbc.class})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class PersonDaoJdbcTest {

    @Autowired
    private PersonDao personDao;

    @Test
    public void checkCount(){
        int result = personDao.count();
        assertEquals(result, 1L);
    }
}
	 */
	
	@Test
	void count() {
		assertNotNull(genreMapper);
		
		//genreDao = context.getBean("genreDaoJdbc", GenreDaoJdbc.class);
		//assertEquals(3, genreDao.count());
		
		//fail("Not yet implemented");
	}

}
