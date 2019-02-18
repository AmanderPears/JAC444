package main;

import java.io.IOException;

import core.LetterCount;
import core.Numbers;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Numbers.generateNumberCombos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			LetterCount.readFile("output.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
