package classes;

public class Bank {

	public int id;
	public double balance, total;
	public int banksLoaned;
	public LoanInfo[] loans;
	int index;
	public boolean unsafe;

	Bank(int id, double balance) {
		if (id >= 0 && balance > 0) {
			this.id = id;
			this.balance = balance;
			this.banksLoaned = 0;
			this.index = 0;
			this.total = this.balance;
			this.unsafe = true;
		} else {
			this.id = -1;
			this.balance = -1;
			this.banksLoaned = -1;
			this.index = -1;
			this.total = -1;
			this.unsafe = true;
		}
	}

	public void setBanksLoaned(int n) {
		if (n > 0 && n <= Banks.numOfBanks) {
			this.banksLoaned = n;
			this.loans = new LoanInfo[n];
		}
	}

	public void addLoan(int id, double amt) {
		if (this.index < this.banksLoaned) {
			this.loans[index++] = new LoanInfo(id, amt);
			this.total += amt;
			if (this.total > Banks.minAssetLimit)
				this.unsafe = false;
		}
	}

}
