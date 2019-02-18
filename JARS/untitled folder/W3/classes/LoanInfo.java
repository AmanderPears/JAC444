package classes;

public class LoanInfo {
	public int bankId;
	public double amt;

	public LoanInfo(int i, double d) {
		if (i >= 0 && d > 0) {
			bankId = i;
			amt = d;
		} else {
			bankId = -1;
			amt = -1;
		}
	}
}
