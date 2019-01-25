package old;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Vector;

class HMAlphabet {
	public char value;
	public boolean guessed;

	public HMAlphabet(char c) {
		value = c;
		guessed = false;
	}

	@Override
	public String toString() {
		return guessed ? Character.toString(value) : "*";
	}
}

class HMWord {
	public HMAlphabet[] hmword;

	public HMWord(char[] charArray) {
		hmword = new HMAlphabet[charArray.length];
		for (int i = 0; i < charArray.length; i++) {
			hmword[i] = new HMAlphabet(charArray[i]);
		}
	}

	public boolean guess(char c) {
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

	public boolean guessed() {
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

public class Task01_NEW {

	private static String[] dictionary;

	public Task01_NEW(Scanner getInput) {
		importDictionary();
		// setup hangman word
		int index = (int) (Math.random() * dictionary.length);
		HMWord word = new HMWord(dictionary[index].toLowerCase().toCharArray());

		int miss = 0;
		boolean badGuess;
		do {
			System.out.format("(Guess) Enter a letter in word %s > ", word);
			badGuess = !word.guess(getInput.next().toLowerCase().charAt(0));
			miss = badGuess ? miss + 1 : miss;
		} while (!word.guessed() && miss < 8);

		if (miss < 8)
			System.out.format("The word is %s. You missed %d time\n", word, miss);
		else
			System.out.format("The word is %s. You hanged a man...\n", dictionary[index]);
	}

	private void importDictionary() {

		Vector<String> list = new Vector<String>();
		InputStream file = getClass().getClassLoader().getResourceAsStream("resource/dictionary.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(file))) {
			for (String line; (line = br.readLine()) != null;) {
				list.addElement(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dictionary = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			dictionary[i] = list.elementAt(i);
		}
	}

}
