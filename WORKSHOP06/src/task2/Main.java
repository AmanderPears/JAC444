package task2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene((Parent) FXMLLoader.load(Main.class.getResource("Main.fxml"))));
		primaryStage.show();
	}

	public static void closeApp() {
		Platform.exit();
	}

}
