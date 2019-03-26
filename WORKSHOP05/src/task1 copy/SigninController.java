package task1;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SigninController {

	static Stage stage;
	static Account acct;

	void initialize(Stage s, Account a) {
		stage = s;
		acct = a;
	}

	void chgToAccScene() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Account.fxml"));
			Parent root = fxmlLoader.load();
			AccountController acctControl = fxmlLoader.getController();
			acctControl.initialize(stage, acct);
			stage.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button signinBtn;

	@FXML
	private TextField acctNumField;

	@FXML
	private PasswordField acctPinField;

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
					acct = temp;
					chgToAccScene();
					break;
				}
			}
		}
	}

}
