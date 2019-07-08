package ru.otus.spring.dao;

import java.util.List;

public interface Dao<T> {
	
	int count();
	
	void insert(T t);
	
	T getById(int id);
	
	List<T> getAll();
	
	void deleteById(int id);

	T getByName(String name);
}
