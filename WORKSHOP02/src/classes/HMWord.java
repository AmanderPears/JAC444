package classes;

public class HMWord {
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
