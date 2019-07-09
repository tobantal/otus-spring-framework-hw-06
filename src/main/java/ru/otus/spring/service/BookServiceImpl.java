package ru.otus.spring.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
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
	
	private ConcurrentMap<String, Author> authors;
	private ConcurrentMap<String, Genre> genres;
	
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
	public void addBook(String name, String author, String genre) {
		Book book = new Book(name, getAuthorByName(author).getId(), getGenreByName(genre).getId());
		bookDao.insert(book);
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
		// TODO Auto-generated method stub
		authors = authorDao.getAll().stream().collect(Collectors.toConcurrentMap(Author::getName, a->a));
		genres = genreDao.getAll().stream().collect(Collectors.toConcurrentMap(Genre::getName, g->g));
	}
	
	private Author getAuthorByName(String author) {
		Author a;
		if(authors.containsKey(author)) {
			a = authors.get(author);
		} else {
			authorDao.insert(new Author(author));
			a = authorDao.getByName(author);
			authors.put(author, a);
		}
		return a;
	}
	
	private Genre getGenreByName(String genre) {
		Genre g;
		if(genres.containsKey(genre)) {
			g = genres.get(genre);
		} else {
			genreDao.insert(new Genre(genre));
			g = genreDao.getByName(genre);
			genres.put(genre, g);
		}
		return g;
	}
	
}
