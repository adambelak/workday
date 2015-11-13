package com.epam.training;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Scanner;

import com.epam.training.workday.WorkdayProxy;

public class Application {

	private static final String ESCAPE_STRING = ":x";
	private static final String PLEASE_TYPE_MESSAGE = "Type a date (or {0} to exit): ";
	private static final String ANSWER_PATTERN = "{0} is {1} workday";
	private static final String NOT_A = "not a";
	private static final String A = "a";

	public static void main(String[] args) throws IOException {
		new Application().run();
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		String line = null;
		do {
			printMessage();
			line = scanner.nextLine();
			printResult(line);
		} while (line != null && !line.equals(ESCAPE_STRING));
		scanner.close();
	}

	private void printMessage() {
		System.out.print(MessageFormat.format(PLEASE_TYPE_MESSAGE, ESCAPE_STRING));
	}

	private void printResult(String line) {
		if (!line.equals(ESCAPE_STRING)) {
			boolean result = WorkdayProxy.getInstance().checkDateIsWorkday(line);
			System.out.println(MessageFormat.format(ANSWER_PATTERN, line, (result ? A : NOT_A)));
		}
	}

}
