package classes;

public class CreditCardNumber {
	public static final int VISA = 4, MASTER = 5, AMERICAN = 37, DISCOVER = 6, MAX_DIGITS = 16, MIN_DIGITS = 13;

	// 1. Return true if card number is valid
	public static boolean isValid(Long n) {
		// check length first
		int l = length(n);
		if (MIN_DIGITS <= l && l <= MAX_DIGITS) {
			// check the prefix
			int p = (int) getDigits(n, 1);
			p = p == 3 ? (int) getDigits(n, 2) : p;
			if (isPrefix(p)) {
				// Mod 10 check on the sum
				int sum = getEvenSum(n) + getOddsum(n);
				return sum % 10 == 0;
			}
		}

		return false;
	}

	// 2. Get result from step 2
	private static int getEvenSum(Long n) {
		return getSum(n, false);
	}

	// 3. Return this number if it is a single digit, otherwise, return the sum of
	// the two digits
	private static int getSingleDigit(int i) {
		return (i % 10) + (i / 10);
	}

	// 4. Return sum of odd-place digits in number
	private static int getOddsum(Long n) {
		return getSum(n, true);
	}

	// 5. Return true if the digit is a prefix for a number
	private static boolean isPrefix(int p) {
		return (p == VISA || p == MASTER || p == AMERICAN || p == DISCOVER);
	}

	// 6. Return the number of digits
	public static int length(Long n) {
		return Long.toString(n).length();
	}

	// 7. Return the first k number of digits from number. If the number of digits
	// in number is less than k, return number.
	private static long getDigits(Long n, int k) {
		if (k > 0 && k < length(n)) {
			return Long.divideUnsigned(n, (long) Math.pow(10, length(n) - k));
		} else {
			return n;
		}
	}

	// other methods

	// get sum
	private static int getSum(Long n, boolean odd) {
		int digit, sum = 0;
		n = odd ? n : n / 10;
		int nlength = length(n);
		for (int i = 0; i < nlength; i++) {
			if (i % 2 == 0) {
				digit = (int) (n % 10);
				digit = !odd ? 2 * digit : digit;
				sum += getSingleDigit(digit);
			}
			n /= 10;
		}
		return sum;
	}

	// create card
	public static long getNewCardNumber(final int c) {
		int clength = (int) (Math.random() * (17 - 13) + 13);
		clength = clength - 1 - length((long) c);
		long n = (long) (Math.random() * Math.pow(10, clength));
		n += c * Math.pow(10, clength);
		n *= 10;
		clength = getEvenSum(n) + getOddsum(n);
		clength = clength % 10;
		clength = clength == 0 ? 0 : (10 - clength);

		return n += clength;
	}
}
