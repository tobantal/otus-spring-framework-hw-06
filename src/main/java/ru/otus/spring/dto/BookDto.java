package ru.otus.spring.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookDto {
	
    private final int id;
    private final String name;
    private final String author;
    private final String genre;

}
