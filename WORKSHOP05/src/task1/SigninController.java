package task1;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SigninController {

	@FXML
	private Button signinBtn;

	@FXML
	private TextField acctNumField;

	@FXML
	private PasswordField acctPinField;
	
	@FXML
	private Label infoLabel;
	
	@FXML
	void initialize() {
		infoLabel.setText("Enter valid ID and PIN");
	}

	@FXML
	void signinAction() throws Exception {
		int num = (!acctNumField.getText().isEmpty()) ? Integer.parseInt(acctNumField.getText()) : 0,
				pin = (!acctPinField.getText().isEmpty()) ? Integer.parseInt(acctPinField.getText()) : 0;

		try (FileInputStream file = new FileInputStream("account.dat");
				ObjectInputStream in = new ObjectInputStream(file);) {

			Account temp = null;
			while (file.available() != 0) {
				temp = (Account) in.readObject();
				if (temp.get_id() == num && temp.get_pin() == pin) {
					Main.acct = temp;
					Main.changeScene("Account.fxml");
					break;
				}
			}
			
			infoLabel.setText("Account not found");
		}
	}

}
