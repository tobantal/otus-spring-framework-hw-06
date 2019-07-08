package ru.otus.spring.service;

import java.io.PrintStream;

import org.springframework.stereotype.Service;

@Service
public class ConsoleServiceImpl implements ConsoleService {
	
	private final PrintStream out = System.out;
	
	public void write(String msg, Object... args) {
		this.out.print("> ");
		this.out.print(ANSI_GREEN);
		this.out.printf(msg, args);
		this.out.print(ANSI_RESET);
		this.out.println();
	}
	
}
