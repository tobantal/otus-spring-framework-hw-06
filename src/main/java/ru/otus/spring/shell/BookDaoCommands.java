package ru.otus.spring.shell;

import java.util.List;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Person;

@ShellComponent
@RequiredArgsConstructor
public class BookDaoCommands {
	
	private final BookDao bookDao;
	private final ConsoleService consoleService;
	
	@ShellMethod("count books")
	public void count() {
		consoleService.write("books are %d", bookDao.count());
	}
	
	@ShellMethod("insert new book")
	public void put(String id, String name) {
		Book book = new Book(Integer.parseInt(id), name);	
		bookDao.insert(book);
		consoleService.write("book %s has been saved", book.getName());
	}
	
	@ShellMethod("find book by id")
	public void get(String id) {
		Book book = bookDao.getById(Integer.parseInt(id));
		consoleService.write("book %s has been found", book.getName());
	}
	
	@ShellMethod("find all books")
	public void getAll() {
		List<Book> books = bookDao.getAll();
		books.forEach(book -> consoleService.write("[%d] %s", book.getId(), book.getName()));
		
	}
	
	@ShellMethod("delete book by id")
	public void delete(int id) {
		Book book = bookDao.getById(id);
		bookDao.deleteById(id);
		consoleService.write("person %s has been found and deleted", book.getName());
	}
}
