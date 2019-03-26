package task1;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static double annualInterestRate = 0;

	private int id, pin;
	private double balance;
	private Date dateCreated;

	public String firstName, lastName;

	public Account() {
		id = 0;
		pin = 0;
		balance = 0.0;
		dateCreated = null;
		firstName = null;
		lastName = null;
	}

	public Account(int id, int pin, double balance) {
		// call default constructor
		this();

		// need checks?
		this.id = id;
		this.pin = pin;
		this.balance = balance;
		this.dateCreated = new Date();
	}

	public void set_fName(String s) {
		firstName = s;
	}

	public void set_lName(String s) {
		lastName = s;
	}

	public void set_id(int id) {
		// need check?
		this.id = id;
	}

	public int get_id() {
		return id;
	}

	public void set_pin(int pin) {
		// need check?
		this.pin = pin;
	}

	public int get_pin() {
		return pin;
	}

	public void set_balance(double balance) {
		// need check?
		this.balance = balance;
	}

	public double get_balance() {
		return balance;
	}

	public static void set_rate(double rate) {
		// need check?
		annualInterestRate = rate;
	}

	public static double get_rate() {
		return annualInterestRate;
	}

	public Date get_date() {
		return dateCreated;
	}

	public double getMonthlyInterestRate() {
		return (annualInterestRate / 12);
	}

	public double getMonthlyInterest() {
		return (balance * getMonthlyInterestRate());
	}

	public void withdraw(double amt) {
		if (amt > 0) {
			balance = (balance - amt) > 0 ? balance - amt : balance;
		}
	}

	public void deposit(double amt) {
		balance = (amt > 0) ? balance + amt : balance;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Account Details\n");
		sb.append("===============\n");
		sb.append("ID: ").append(Integer.toString(id)).append("\n");
		sb.append("PIN: ").append(Integer.toString(pin)).append("\n");
		sb.append("Balance: $").append(Double.toString(balance)).append("\n");
		sb.append("Holder: ").append(firstName == null ? "no" : firstName).append(" ");
		sb.append(lastName == null ? "name" : lastName).append("\n");

		return sb.toString();
	}

}
