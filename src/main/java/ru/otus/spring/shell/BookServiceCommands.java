package ru.otus.spring.shell;

import java.util.List;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.ConsoleServiceImpl;
import ru.otus.spring.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class BookServiceCommands {
	
	private final ConsoleServiceImpl consoleService;
	private final BookService bookService;
	private final AuthorService authorService;
	private final GenreService genreService;
	
	
	@ShellMethod("count books")
	public void countBooks() {
		consoleService.write("books are %d", bookService.size());
	}
	
	@ShellMethod("add new book")
	public void addBook(String name, String author, String genre) {
		bookService.addBook(name, author, genre);
		consoleService.write("book %s has been saved", name);
	}
	
	@ShellMethod("find book by name")
	public void findBook(String name) {
		Book b = bookService.findBookByName(name);
		consoleService.write("%s", b.toString());
	}
	
	@ShellMethod("find book by author")
	public void findBooksByAuthor(String author) {
		List<Book> books = bookService.findBooksByAuthor(author);
		books.forEach(b -> consoleService.write("%s",  b.toString()));	
	}
	
	@ShellMethod("find book by genre")
	public void findBooksByGenre(String genre) {
		List<Book> books = bookService.findBooksByGenre(genre);
		books.forEach(b -> consoleService.write("%s",  b.toString()));	
	}
	
	@ShellMethod("find all books")
	public void findAllBooks() {
		List<Book> books = bookService.findAllBooks();
		books.forEach(b -> consoleService.write("%s",  b.toString()));		
	}
	
	@ShellMethod("find all authors")
	public void findAllAuthors() {
		List<Author> authors = authorService.findAll();
		authors.forEach(a -> consoleService.write("%s",  a.toString()));		
	}
	
	@ShellMethod("find all genres")
	public void findAllGenres() {
		List<Genre> genres = genreService.findAll();
		genres.forEach(g -> consoleService.write("%s",  g.toString()));		
	}
	
	@ShellMethod("delete book by id")
	public void deleteBook(int id) {
		bookService.deleteBookById(id);
		consoleService.write("book with id=%s has been deleted", id);
	}
	
	@ShellMethod("delete author by id")
	public void deleteAuthor(String id) {
		authorService.deleteById(Integer.parseInt(id));
		consoleService.write("author with id=%s has been deleted", id);
	}
	
	@ShellMethod("delete genre by id")
	public void deleteGenre(String id) {
		genreService.deleteById(Integer.parseInt(id));
		consoleService.write("genre with id=%s has been deleted", id);
	}
}
