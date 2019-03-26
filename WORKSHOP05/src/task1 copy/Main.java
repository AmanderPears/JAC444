package task1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	static Stage stage;
	static Account acct;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signin.fxml"));
			Parent root = fxmlLoader.load();
			SigninController control = fxmlLoader.getController();
			control.initialize(stage, acct);
			stage.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
		stage.show();
	}

}
//ID: 8786836
//PIN: 5785