package ru.otus.spring.service;

import java.util.List;
import java.util.stream.Collectors;

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
public class BookServiceImpl implements BookService {
	
	private final BookDao bookDao;
	private final AuthorDao authordao;
	private final GenreDao genreDao;
	
	@Override
	public int size() {
		return bookDao.count();
	}

	@Override
	public BookDto findBookByName(String name) {
		Book b = bookDao.getByName(name);
		Author a = authordao.getById(b.getAuthorId());
		Genre g = genreDao.getById(b.getGenreId());
		return new BookDto(b.getId(), b.getName(), a.getName(), g.getName());
	}

	@Override
	public void addBook(String name, String author, String genre) {
		// TODO Auto-generated method stub
		Author a = authordao.getByName(author);
		// or insert new author
		Genre g = genreDao.getByName(genre);
		// or insert new genre
		Book book = new Book(name, a.getId(), g.getId());
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
					Author a = authordao.getById(b.getAuthorId());
					Genre g = genreDao.getById(b.getGenreId());
					return new BookDto(b.getId(), b.getName(), a.getName(), g.getName());
		}).collect(Collectors.toList());
	}
	
}
