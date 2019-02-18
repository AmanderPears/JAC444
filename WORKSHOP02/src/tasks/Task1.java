package tasks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Vector;

import classes.HMWord;

public class Task1 {

	private static String[] dictionary;

	public Task1(Scanner getInput) {
		importDictionary();
		// setup hangman word
		int index = (int) (Math.random() * dictionary.length);
		HMWord word = new HMWord(dictionary[index].toLowerCase().toCharArray());

		int miss = 0;
		boolean badGuess;
		do {
			// System.out.format("(Guess) Enter a letter in word %s > ", word);
			System.out.format("(Lives:%d) Guess a letter in word %s > ", 8 - miss, word);
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
