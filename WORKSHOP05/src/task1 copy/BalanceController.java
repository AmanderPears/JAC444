package task1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BalanceController {

	static Stage stage;
	static Account acct;

	void initialize(Stage s, Account a) {
		stage = s;
		acct = a;

		//
		dateLabel.setText(acct.get_date().toString());
		balanceLabel.setText(String.format("%7.2f", acct.get_balance()));
		nameLabel.setText(acct.firstName + " " + acct.lastName);
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
			if (accts.get(i).get_id() == acct.get_id()) {
				accts.get(i).set_fName(acct.firstName);
				accts.get(i).set_lName(acct.lastName);
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

	@FXML
	private Label dateLabel, balanceLabel, nameLabel;

	@FXML
	private TextField lNameFld, fNameFld;

	@FXML
	void setfName() throws Exception {
		acct.firstName = fNameFld.getText();
		fNameFld.setText(null);
		nameLabel.setText(acct.firstName + " " + acct.lastName);
		writeData();
	}

	@FXML
	void setlName() throws Exception {
		acct.lastName = lNameFld.getText();
		lNameFld.setText(null);
		nameLabel.setText(acct.firstName + " " + acct.lastName);
		writeData();
	}

	@FXML
	void backBtnAction() throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Account.fxml"));
		Parent root = fxmlLoader.load();
		AccountController control = fxmlLoader.getController();
		control.initialize(stage, acct);
		stage.setScene(new Scene(root));
	}

}
