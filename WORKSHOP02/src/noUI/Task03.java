package noUI;

import java.util.Scanner;

public class Task03 {

	private Long cardNumber;
	private int length;
	private int[] numList;

	public Task03(Scanner getInput) {

		// prompt for input, validate, and store
		cardNumber = promptInput("Enter a credit card number as a long integer: ", getInput);

		// calculate length of cardNumber
		length = getCardNumberLength(cardNumber);

		numList = new int[length];

		System.out.println("Card is " + (isValid() ? "valid." : "invalid."));

		// System.out.println("first 5 numbers: " + subNum(cardNumber, 5));
		// System.out.println("first 20 numbers: " + subNum(cardNumber, 20));

	}

	private Long promptInput(String prompt, Scanner getInput) {
		int length = 0;
		Long val = 0L;

		while (length < 13 || length > 16 || !isPrefix(val)) {
			System.out.print(prompt);

			while (!getInput.hasNextLong()) {
				System.out.print(prompt);
				getInput.next();
			}

			val = getInput.nextLong();
			length = getCardNumberLength(val);

		}

		return val;
	}

	// 1. Return true if the card number is valid
	private boolean isValid() {

		int sum;
		Long backup = cardNumber;
		for (int i = 0; i < length; i++) {
			// get value
			numList[i] = (int) (cardNumber % 10);
			cardNumber = cardNumber / 10;

			// even
			if (i % 2 != 0) {
				numList[i] = getSingle(numList[i]);
			}
		}

		sum = getEvenSum(numList) + getOddSum(numList);

		// restore
		cardNumber = backup;
		return sum % 10 == 0;
	}

	// 2.Get the result from Step 2
	private int getEvenSum(int[] list) {
		int sum = 0;
		for (int i = 0; i < list.length; i++) {
			if (i % 2 != 0) {
				sum += list[i];
			}
		}
		return sum;
	}

	// 3. Return this number if it is a single digit,
	// otherwise, return the sum of the two digits
	private int getSingle(int i) {
		i += i;
		if (i > 9) {
			int temp = 0;
			for (int j = 0; j < 2; j++) {
				temp += i % 10;
				i = i / 10;
			}
			i = temp;
		}

		return i;
	}

	// 4. Return sum of odd-place digits in number
	private int getOddSum(int[] list) {
		int sum = 0;
		for (int i = 0; i < list.length; i++) {
			if (i % 2 == 0) {
				sum += list[i];
			}
		}
		return sum;
	}

	// 5. Return true if the digit is a prefix for number
	private boolean isPrefix(Long n) {
		char[] digits = Long.toString(n).toCharArray();
		int temp = Character.getNumericValue(digits[0]);
		if (temp == 4 || temp == 5 || temp == 6) {
			return true;
		} else if (temp == 3 && Character.getNumericValue(digits[1]) == 7) {
			return true;
		} else {
			return false;
		}
	}

	// 6. Return the number of digits
	private int getCardNumberLength(Long n) {
		return Long.toString(n).length();
	}

	// 7. Return the first k number of digits from number.
	// If the number of digits in number is less than k,
	// return number.
	private long subNum(Long n, int k) {
		String s = Long.toString(n);
		if (k <= s.length()) {
			s = Long.toString(n).substring(0, k);
		}

		return Long.parseLong(s);
	}

}
