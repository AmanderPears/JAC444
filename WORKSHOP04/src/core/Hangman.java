package core;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Vector;

//class that handles the appearance of a guessed character
class HMAlphabet {
	char value;
	boolean guessed;

	HMAlphabet(char c) {
		value = c;
		guessed = false;
	}

	@Override
	public String toString() {
		return guessed ? Character.toString(value) : "*";
	}
}

//uses above class to handle words
class HMWord {
	HMAlphabet[] hmword;

	HMWord(char[] charArray) {
		hmword = new HMAlphabet[charArray.length];
		for (int i = 0; i < charArray.length; i++) {
			hmword[i] = new HMAlphabet(charArray[i]);
		}
	}

	boolean guess(char c) {
		// check if c was already guessed, return true and exit
		for (char i : toString().toCharArray()) {
			if (i == c) {
				System.out.format("     %s is already in the word\n", c);
				return true;
			}
		}

		// check if guess uncovers letters
		boolean found = false;
		for (HMAlphabet i : hmword) {
			if (i.value == c) {
				i.guessed = true;
				found = true;
			}
		}

		// if guess was incorrect print message and return false
		if (!found)
			System.out.format("     %s is not in the word\n", c);

		return found;
	}

	boolean guessed() {
		for (HMAlphabet i : hmword) {
			if (!i.guessed) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString() {
		String val = "";

		for (HMAlphabet i : hmword) {
			val += i.toString();
		}

		return val;
	}
}

//main class that read from file and runs the game
public class Hangman {

	private static String[] dictionary;

	public Hangman(Scanner getInput) {

		System.out.println("Loading words from file...");
		importDictionary();

		if (dictionary.length == 0) {
			System.out.println("Error loading words... exiting");
		} else {
			System.out.println("Words loaded from file.\n");

			// setup hangman word
			int index = (int) (Math.random() * dictionary.length);
			HMWord word = new HMWord(dictionary[index].toLowerCase().toCharArray());

			int miss = 0;
			boolean badGuess;
			do {
				System.out.format("(Lives:%d) Guess a letter in word %s > ", 8 - miss, word);
				badGuess = !word.guess(getInput.next().toLowerCase().charAt(0));
				miss = badGuess ? miss + 1 : miss;
			} while (!word.guessed() && miss < 8);

			if (miss < 8)
				System.out.format("The word is %s. You missed %d time\n", word, miss);
			else
				System.out.format("The word is %s. You hanged a man...\n", dictionary[index]);
		}
	}

	private void importDictionary() {
		// prevent reloading static variable
		if (dictionary == null) {
			Vector<String> list = new Vector<String>();

			InputStream file = getClass().getClassLoader().getResourceAsStream("resource/hangman.txt");
			try (Scanner in = new Scanner(file)) {
				while (in.hasNext()) {
					list.add(in.next());
				}
				in.close();
			} catch (Exception e) {
				System.out.println("******************ERROR*****************");
				System.out.println(e);
				System.out.println("File could not be found");
				System.out.println("****************************************");
			}

			dictionary = new String[list.size()];
			list.toArray(dictionary);
		}
	}
}
