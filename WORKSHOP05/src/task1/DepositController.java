package task1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepositController {

	@FXML
	private Label infoLabel, idLabel, balanceLabel;

	@FXML
	private TextField amountFld;

	@FXML
	void initialize() {
		updateVisuals(); // visuals
	}

	@FXML
	void depositBtnAction() throws Exception {
		/*
		 * Process then display changes
		 */
		double amount = !amountFld.getText().isEmpty() ? Double.parseDouble(amountFld.getText()) : 0;
		Main.acct.deposit(amount);

		// read all accounts
		ArrayList<Account> accts = new ArrayList<Account>();
		try (FileInputStream file = new FileInputStream("account.dat");
				ObjectInputStream in = new ObjectInputStream(file);) {

			Account temp;
			while (file.available() != 0) {
				try {
					temp = (Account) in.readObject();
					accts.add(temp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// update matching
		for (int i = 0; i < accts.size(); i++) {
			if (accts.get(i).get_id() == Main.acct.get_id()) {
				accts.get(i).set_balance(Main.acct.get_balance());
				break;
			}
		}

		// write
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("account.dat"))) {
			for (Account a : accts) {
				out.writeObject(a);
			}
		}

		/*
		 * Display changes if no errors
		 */
		updateVisuals();
	}

	@FXML
	void backBtnAction() throws Exception {
		Main.changeScene("Account.fxml");
	}

	void updateVisuals() {
		idLabel.setText(Integer.toString(Main.acct.get_id()));
		balanceLabel.setText("$" + Double.toString(Main.acct.get_balance()));
		amountFld.setText(null);
	}

}
