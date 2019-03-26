package task1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AccountController {

	@FXML
	public Label welcomeLabel;

	@FXML
	void initialize() {
		welcomeLabel.setText("Welcome " + Main.acct.firstName + " " + Main.acct.lastName);
	}

	@FXML
	void checkBalanceButtonAction() throws Exception {
		Main.changeScene("Balance.fxml");
	}

	@FXML
	void withdrawBtnAction() throws Exception {
		Main.changeScene("Withdraw.fxml");
	}

	@FXML
	void depositBtnAction() throws Exception {
		Main.changeScene("Deposit.fxml");
	}

	@FXML
	void exitAcctAction() throws Exception {
		Main.changeScene("Signin.fxml");
	}

}
