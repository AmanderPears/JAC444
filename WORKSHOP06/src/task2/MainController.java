package task2;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {

	@FXML
	private TextField yearField, nameField, genderField;

	@FXML
	private TextArea infoLabel;

	@FXML
	void submitSearch() {
		String year = yearField.getText(), name = nameField.getText(), gender = genderField.getText();

		if (year != null && year.length() > 0 && name != null && name.length() > 0 && gender != null
				&& gender.length() > 0) {
			infoLabel.setText(Search.searchFile(Integer.parseInt(year), gender.charAt(0), name));
		} else {
			infoLabel.setText("Check query...");
		}
	}

	@FXML
	void clearFields() {
		yearField.setText(null);
		nameField.setText(null);
		genderField.setText(null);
		infoLabel.setText(null);
	}

	@FXML
	void exitApp() {
		Main.closeApp();
	}

}
