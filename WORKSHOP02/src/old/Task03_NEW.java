package old;

import java.util.Scanner;

public class Task03_NEW {

	private Long cardNumber;
	private int length;
	private int[] numList;

	public Task03_NEW(Scanner getInput) {
		int newInput = 0;
		while (newInput < 1 || newInput > 2) {
			newInput = 0;
			System.out.println("1. Validate card");
			System.out.println("2. Generate card");
			System.out.print("Please make selection: ");
			while (!getInput.hasNextInt()) {
				System.out.print("Please make selection: ");
				getInput.next();
			}
			newInput = getInput.nextInt();
			if (newInput == 1) {

				// prompt for input, validate, and store
				cardNumber = promptInput("Enter a credit card number as a long integer: ", getInput);

				// calculate length of cardNumber
				length = getCardNumberLength(cardNumber);

				numList = new int[length];

				System.out.println("Card is " + (isValid() ? "valid." : "invalid."));

				// System.out.println("first 5 numbers: " + subNum(cardNumber, 5));
				// System.out.println("first 20 numbers: " + subNum(cardNumber, 20));

			} else if (newInput == 2) {
				int in = 0;
				while (in < 1 || in > 4) {
					in = 0;
					System.out.println("\nChoose type:");
					System.out.println("1. American Express");
					System.out.println("2. Discover Card");
					System.out.println("3. Master Card");
					System.out.println("4. VISA");
					System.out.print("Enter selection: ");
					while (!getInput.hasNextInt()) {
						System.out.print("Enter selection: ");
						getInput.next();
					}
					in = getInput.nextInt();
				}

				if (in == 1) {
					generate(37, 15);
				} else if (in == 2) {
					generate(6011, 19);
				} else if (in == 3) {
					generate((int) (Math.random() * 56 + 51), 16);
				} else {
					generate(4, 16);
				}

				System.out.format("\nGenerated a visa card: %d\n", cardNumber);
				System.out.format("The card is %s\n", isValid() ? "valid" : "invalid");

			}
		}
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
//	private long subNum(Long n, int k) {
//		String s = Long.toString(n);
//		if (k <= s.length()) {
//			s = Long.toString(n).substring(0, k);
//		}
//
//		return Long.parseLong(s);
//	}

	// 8.generate card
	private void generate(int prefix, int len) {

		int pow = len - Integer.toString(prefix).length() - 1;
		cardNumber = (long) (Math.random() * (long) Math.pow(10, pow));

		// visa card
		cardNumber += (long) Math.pow(10, pow) * prefix;
		cardNumber *= 10;

		length = Long.toString(cardNumber).length();
		numList = new int[length];

		// System.out.println(cardNumber);
		// System.out.println(length);

		// System.out.format("%s\n", isValid() ? "valid" : "invalid");
		isValid();
		int digit = getEvenSum(numList) + getOddSum(numList);
		digit = digit % 10;
		digit = (10 - digit) == 10 ? 0 : (10 - digit);
		cardNumber += digit;
	}

}
