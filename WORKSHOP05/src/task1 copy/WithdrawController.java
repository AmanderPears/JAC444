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

public class WithdrawController {

	static Account acct;
	static Stage stage;

	public void initialize(Stage s, Account a) {
		stage = s;
		acct = a;

		// visuals
		updateVisuals();
	}

	void updateVisuals() {
		idLabel.setText(Integer.toString(acct.get_id()));
		balanceLabel.setText("$" + Double.toString(acct.get_balance()));
		amountFld.setText(null);
	}

	@FXML
	private Label infoLabel;

	@FXML
	private Label idLabel;

	@FXML
	private TextField amountFld;

	@FXML
	private Label balanceLabel;

	@FXML
	void withdrawBtnAction() throws Exception {
		double amount = !amountFld.getText().isEmpty() ? Double.parseDouble(amountFld.getText()) : 0;

		if (amount < acct.get_balance()) {
			/*
			 * Process then display changes
			 */
			acct.withdraw(amount);

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
					accts.get(i).set_balance(acct.get_balance());
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
			 * update visuals if no error
			 */
			updateVisuals();
		}
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
