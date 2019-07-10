package ru.otus.spring.shell;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookDto;
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
	
	
	@ShellMethod("find all books")
	public void findAllBooks() {
		List<Book> books = bookService.findAllBooks();
		books.forEach(b -> consoleService.write("%s",  b.toString()));		
	}
	
	@ShellMethod("delete book by id")
	public void delete(int id) {
		bookService.deleteBookById(id);
		consoleService.write("book [%d] has been found and deleted", id);
	}
	
	@ShellMethod("count authors")
	public void countAuthors() {
		consoleService.write("authors are %d", authorService.size());
	}
}
