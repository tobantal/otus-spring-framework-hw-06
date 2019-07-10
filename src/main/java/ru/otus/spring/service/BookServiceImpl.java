package ru.otus.spring.service;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.dto.BookDto;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService, InitializingBean {
	
	private final BookDao bookDao;
	private final AuthorDao authorDao;
	private final GenreDao genreDao;
	
	private final GenreService genreService;
	private final AuthorService authorService;
	
	private ConcurrentMap<String, Book> books;
	
	@Override
	public int size() {
		return bookDao.count();
	}

	@Override
	public BookDto findBookByName(String name) {
		Book b = bookDao.getByName(name);
		Author a = authorDao.getById(b.getAuthorId());
		Genre g = genreDao.getById(b.getGenreId());
		return new BookDto(b.getId(), b.getName(), a.getName(), g.getName());
	}

	@Override
	public void addBook(String name, String author, String genre) { // <- Book
		if(books.containsKey(name)) {
			throw new IllegalArgumentException("Book already exists");
		} else {
			Book book = new Book(name, authorService.getAuthorByName(author).getId(), genreService.getGenreByName(genre).getId());
			bookDao.insert(book);
			books.put(name, book);
		}
	}

	@Override
	public void deleteBookById(int id) {
		bookDao.deleteById(id);
	}

	@Override
	public List<BookDto> findAllBooks() {
		List<Book> books = bookDao.getAll();
		return books.stream()
				.map(b->{
					Author a = authorDao.getById(b.getAuthorId());
					Genre g = genreDao.getById(b.getGenreId());
					return new BookDto(b.getId(), b.getName(), a.getName(), g.getName());
		}).collect(Collectors.toList());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		books = bookDao.getAll().stream().collect(Collectors.toConcurrentMap(Book::getName, b->b));
	}
	
}
