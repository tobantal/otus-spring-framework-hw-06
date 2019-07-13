package ru.otus.spring.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.mapper.BookMapper;

@JdbcTest
@Import({BookDaoJdbc.class, BookMapper.class})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BookDaoJdbcTest {
	
	@Autowired
	BookDao bookDao;

	@Test
	public void testInsert() {
		Author author = new Author(1L, "Ivanov");
		Genre genre = new Genre(1L, "comics");
		Book book = new Book("YoYo", author, genre);
		int countBefore = bookDao.count();
		bookDao.insert(book);
		assertEquals(countBefore + 1, bookDao.count());
	}
	
	@Test
	public void testGetById() {
		Book book = bookDao.getById(1L);
		assertNotNull(book);
		assertEquals("Desert rose", book.getName());
	}
	
	@Test
	public void testGetAll() {
		List<Book> books = bookDao.getAll();
		int count = bookDao.count();
		assertEquals(count, books.size());
	}

	@Test
	public void testDeleteById() {
		int countBefore = bookDao.count();
		bookDao.deleteById(3L);
		assertEquals(countBefore - 1, bookDao.count());
	}

	@Test
	public void testGetByName() {
		String name = "Desert rose";
		Book book = bookDao.getByName(name);
		assertNotNull(book);
		assertThat(book.getId()).isEqualTo(1L);
	}

	@Test
	public void testGetAllByAuthor() {
		String name = "Petrov";
		List<Book> books = bookDao.getAllByAuthor(name);
		assertEquals(1, books.size());
	}

	@Test
	public void testGetAllByGenre() {
		String name = "fantasy";
		List<Book> books = bookDao.getAllByGenre(name);
		assertEquals(1, books.size());
	}
}
