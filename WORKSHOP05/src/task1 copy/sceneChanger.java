package task1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class sceneChanger {
	protected static Stage stage;
	protected static Account acct;

	protected void chgToSiginScene() throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signin.fxml"));
		Parent root = fxmlLoader.load();
		SigninController control = fxmlLoader.getController();
		control.initialize(stage, acct);
		stage.setScene(new Scene(root));
	}

	protected void chgToAccountScene() throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Account.fxml"));
		Parent root = fxmlLoader.load();
		AccountController acctControl = fxmlLoader.getController();
		acctControl.initialize(stage, acct);
		stage.setScene(new Scene(root));
	}

	protected void chgToBalanceScene() throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Balance.fxml"));
		Parent root = fxmlLoader.load();
		BalanceController control = fxmlLoader.getController();
		control.initialize(stage, acct);
		stage.setScene(new Scene(root));
	}

	protected void chgToWithdrawScene() throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Withdraw.fxml"));
		Parent root = fxmlLoader.load();
		WithdrawController control = fxmlLoader.getController();
		control.initialize(stage, acct);
		stage.setScene(new Scene(root));
	}

	protected void chgToDepositScene() throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Deposit.fxml"));
		Parent root = fxmlLoader.load();
		DepositController control = fxmlLoader.getController();
		control.initialize(stage, acct);
		stage.setScene(new Scene(root));
	}
}
