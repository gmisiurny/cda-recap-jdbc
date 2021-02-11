package com.cda.jdbc.ihm;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ihm {
	public static final Ihm IHM_INS = new Ihm();
	private Scanner scanner;
	private boolean displayComment;

	private Ihm() {
		this.scanner = new Scanner(System.in);
	}

	public int readNaturalNb() throws WrongInputException {
		System.out.print("< ");
		int input;
		try {
			input = this.scanner.nextInt();
		} catch (NumberFormatException | InputMismatchException e) {
			this.scanner.nextLine();
			throw new WrongInputException();
		}		
		if (this.scanner.hasNextLine()) {
			String comm = this.scanner.nextLine();
			if (this.displayComment) {
				System.out.print(comm);
			}
		}
		System.out.println();
		return input;
	}
	
	public void display(String message) {
		display(message, true);
	}
	
	public void display(String message, boolean withLineBreak) {
		System.out.print(message+(withLineBreak?"\n":""));
	}
}
