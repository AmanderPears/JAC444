package classes;

public class HMAlphabet {
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
