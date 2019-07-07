package ru.otus.spring.shell;

import java.util.List;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.domain.Person;

@ShellComponent
@RequiredArgsConstructor
public class PersonDaoCommands {
	
	private final PersonDao personDao;
	private final ConsoleService consoleService;
	
	@ShellMethod("count persons")
	public void count() {
		consoleService.write("persons are %d", personDao.count());
	}
	
	@ShellMethod("insert new person")
	public void put(String id, String name) {
		Person person = new Person(Integer.parseInt(id), name);	
		personDao.insert(person);
		consoleService.write("person %s has been saved", person.getName());
	}
	
	@ShellMethod("find person by id")
	public void get(String id) {
		Person person = personDao.getById(Integer.parseInt(id));
		consoleService.write("person %s has been found", person.getName());
	}
	
	@ShellMethod("find all persons")
	public void getAll() {
		List<Person> persons = personDao.getAll();
		persons.forEach(person -> consoleService.write("[%d] %s", person.getId(), person.getName()));
		
	}
	
	@ShellMethod("delete person by id")
	public void delete(int id) {
		Person person = personDao.getById(id);
		personDao.deleteById(id);
		consoleService.write("person %s has been found and deleted", person.getName());
	}
}
