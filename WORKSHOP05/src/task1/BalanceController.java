package task1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BalanceController {

	@FXML
	private Label dateLabel, balanceLabel, nameLabel;

	@FXML
	private TextField lNameFld, fNameFld;

	@FXML
	void initialize() {
		dateLabel.setText(Main.acct.get_date().toString());
		balanceLabel.setText(String.format("%7.2f", Main.acct.get_balance()));
		nameLabel.setText(Main.acct.firstName + " " + Main.acct.lastName);
	}

	@FXML
	void setfName() throws Exception {
		Main.acct.firstName = fNameFld.getText();
		fNameFld.setText(null);
		nameLabel.setText(Main.acct.firstName + " " + Main.acct.lastName);
		writeData();
	}

	@FXML
	void setlName() throws Exception {
		Main.acct.lastName = lNameFld.getText();
		lNameFld.setText(null);
		nameLabel.setText(Main.acct.firstName + " " + Main.acct.lastName);
		writeData();
	}

	@FXML
	void backBtnAction() throws Exception {
		Main.changeScene("Account.fxml");
	}

	/*
	 * write data to file updating it
	 */
	void writeData() throws Exception {

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
				accts.get(i).set_fName(Main.acct.firstName);
				accts.get(i).set_lName(Main.acct.lastName);
				break;
			}
		}

		// write
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("account.dat"))) {
			for (Account a : accts) {
				out.writeObject(a);
			}
		}
	}

}
