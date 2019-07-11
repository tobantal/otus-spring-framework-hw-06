package ru.otus.spring.service;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService, InitializingBean {

	private final BookDao bookDao;
	private final GenreService genreService;
	private final AuthorService authorService;

	private ConcurrentMap<String, Book> books;

	@Override
	public int size() {
		return bookDao.count();
	}

	@Override
	public Book findBookByName(String name) {
		return books.get(name);
	}

	@Override
	public void addBook(String name, String author, String genre) {
		if (books.containsKey(name)) {
			throw new IllegalArgumentException("Book already exists");
		} else {
			Book book = new Book(name, authorService.createIfItIsNecessaryAndGet(author),
					genreService.createIfItIsNecessaryAndGet(genre));
			bookDao.insert(book);
			books = bookDao.getAll().stream().collect(Collectors.toConcurrentMap(Book::getName, b -> b));
			books.put(name, findBookByName(name));
		}
	}

	@Override
	public void deleteBookById(int id) {
		bookDao.deleteById(id);
		books = bookDao.getAll().stream().collect(Collectors.toConcurrentMap(Book::getName, b -> b));
	}

	@Override
	public List<Book> findAllBooks() {
		return bookDao.getAll();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		books = bookDao.getAll().stream().collect(Collectors.toConcurrentMap(Book::getName, b -> b));
	}

	@Override
	public List<Book> findBooksByAuthor(String author) {
		return bookDao.getAllByAuthor(author);
	}

	@Override
	public List<Book> findBooksByGenre(String genre) {
		return bookDao.getAllByGenre(genre);
	}

}
