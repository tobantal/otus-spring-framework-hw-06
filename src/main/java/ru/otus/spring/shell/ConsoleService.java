package ru.otus.spring.shell;

import java.io.PrintStream;

import org.springframework.stereotype.Service;

@Service
public class ConsoleService {

	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	
	private final PrintStream out = System.out;
	
	public void write(String msg, Object... args) {
		this.out.print("> ");
		this.out.print(ANSI_GREEN);
		this.out.printf(msg, args);
		this.out.print(ANSI_RESET);
		this.out.println();
	}
	
}
