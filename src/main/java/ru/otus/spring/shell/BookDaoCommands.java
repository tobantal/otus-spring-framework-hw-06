package ru.otus.spring.shell;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.ConsoleServiceImpl;

@ShellComponent
@RequiredArgsConstructor
public class BookDaoCommands {
	
	private final ConsoleServiceImpl consoleService;
	private final BookService bookService;
	
	
	@ShellMethod("count books")
	public void count() {
		consoleService.write("books are %d", bookService.size());
	}
	
	@ShellMethod("add new book")
	public void addBook(String name, String author, String genre) {
		bookService.addBook(name, author, genre);
		consoleService.write("book %s has been saved", name);
	}
	
	@ShellMethod("find book by name")
	public void findBook(String name) {
		// обращаемся к кэшу ConcurrentHashMap<String, BookDto>(); // name, BookDto
		BookDto b = bookService.findBookByName(name); // bookDto.toString()
		consoleService.write("%s", b.toString());
	}
	
	@ShellMethod("find all books")
	public void findAllBooks() {
		List<BookDto> booksDto = bookService.findAllBooks();
		booksDto.forEach(b -> consoleService.write("[%d] name=%s, author=%s, genre=%s",  b.getId(), b.getName(), b.getAuthor(), b.getGenre()));		
	}
	
	@ShellMethod("delete book by id")
	public void delete(int id) {
		bookService.deleteBookById(id);
		consoleService.write("book [%d] has been found and deleted", id);
	}
}
