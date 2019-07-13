package ru.otus.spring.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

	private final BookDao bookDao;
	private final GenreService genreService;
	private final AuthorService authorService;

	@Override
	public int size() {
		return bookDao.count();
	}

	@Override
	public Book findBookByName(String name) {
		return bookDao.getByName(name);
	}

	@Override
	public Book addBook(String name, String author, String genre) {		
		try {
			return findBookByName(name);
		} catch(EmptyResultDataAccessException e) {
			Book book = new Book(name, authorService.createIfItIsNecessaryAndGet(author),
					genreService.createIfItIsNecessaryAndGet(genre));
			bookDao.insert(book);
			return findBookByName(name);
		}
	}

	@Override
	public void deleteBookById(Long id) {
		bookDao.deleteById(id);
	}

	@Override
	public List<Book> findAllBooks() {
		return bookDao.getAll();
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
