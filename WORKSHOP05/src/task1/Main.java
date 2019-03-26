package task1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	protected static Stage stage;
	protected static Account acct;

	protected static void changeScene(final String fileName) throws Exception {
		stage.setScene(new Scene((Parent) FXMLLoader.load(Main.class.getResource(fileName))));
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		changeScene("Signin.fxml");
		stage.show();
	}

//	@Override
//	public void stop() throws Exception {
//		Platform.exit();
//	}

}
//ID: 8786836
//PIN: 5785

//ID: 274636
//PIN: 5088