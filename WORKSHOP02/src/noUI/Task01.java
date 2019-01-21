package noUI;

import java.util.Scanner;

class hangmanAlphabet {
	private char c;
	public boolean guessed;

	public hangmanAlphabet(char in) {
		c = in;
		guessed = false;
	}

	public boolean guess(char in) {
		if (in == c) {
			guessed = true;
			return true;
		} else {
			return false;
		}
	}

	public char get() {
		if (guessed)
			return c;
		else
			return '*';
	}

	public char val() {
		return c;
	}
}

class hangmanWord {
	private hangmanAlphabet[] word;

	public hangmanWord(String in) {
		word = new hangmanAlphabet[in.length()];
		char[] charArray = in.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			word[i] = new hangmanAlphabet(charArray[i]);
		}
	}

	public String get() {
		String val = "";
		for (hangmanAlphabet c : word) {
			val += c.get();
		}
		return val;
	}

	public boolean solved() {
		for (hangmanAlphabet c : word) {
			if (!c.guessed) {
				return false;
			}
		}

		return true;
	}

	public boolean guess(char c) {
		// check if used already
		for (char i : get().toCharArray()) {
			if (i == c) {
				System.out.format("     %s is already in the word\n", c);
				return true;
			}
		}

		boolean found = false;
		for (hangmanAlphabet i : word) {
			if (i.guess(c)) {
				found = true;
			}
		}

		if (!found)
			System.out.format("     %s is not in the word\n", c);

		return found;
	}
}

public class Task01 {

	private String[] words = { "program", "melissa", "girlfriend" };

	public Task01(Scanner getInput) {
		String temp = words[(int) (Math.random() * words.length)].toLowerCase();
		hangmanWord hgw = new hangmanWord(temp);

		char c;
		int miss = 0;
		while (!hgw.solved()) {
			c = prompt(hgw.get(), getInput);
			miss = !hgw.guess(c) ? miss + 1 : miss;
		}

		System.out.format("\nThe word is %s. You missed %d time.\n", hgw.get(), miss);
	}

	private char prompt(String s, Scanner getInput) {

		System.out.format("(Guess) Enter a letter in word %s > ", s);
		return getInput.next().toLowerCase().charAt(0);
	}
}
