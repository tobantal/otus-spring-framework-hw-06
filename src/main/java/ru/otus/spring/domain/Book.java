package ru.otus.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Book {
	
    private final int id;
    private final String name;
    private final String author;
    private final String genre;

}
