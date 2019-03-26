package task1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AccountController {

	static Stage stage;
	static Account acct;

	public void initialize(Stage s, Account a) {
		stage = s;
		acct = a;

		//
		welcomeLabel.setText("Welcome " + acct.firstName + " " + acct.lastName);
	}

	@FXML
	public Label welcomeLabel;

	@FXML
	void checkBalanceButtonAction() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Balance.fxml"));
			Parent root = fxmlLoader.load();
			BalanceController control = fxmlLoader.getController();
			control.initialize(stage, acct);
			stage.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void withdrawBtnAction() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Withdraw.fxml"));
			Parent root = fxmlLoader.load();
			WithdrawController control = fxmlLoader.getController();
			control.initialize(stage, acct);
			stage.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void depositBtnAction() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Deposit.fxml"));
			Parent root = fxmlLoader.load();
			DepositController control = fxmlLoader.getController();
			control.initialize(stage, acct);
			stage.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void exitAcctAction() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signin.fxml"));
			Parent root = fxmlLoader.load();
			SigninController control = fxmlLoader.getController();
			control.initialize(stage, acct);
			stage.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
