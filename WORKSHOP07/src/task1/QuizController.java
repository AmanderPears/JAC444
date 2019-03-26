package task1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class QuizController {

	@FXML
	private Label titleLabel, addLabel, subLabel, mulLabel, divLabel, correctLabel, incorrectLabel;

	@FXML
	private TextField addFld, subFld, mulFld, divFld;

	@FXML
	void initialize() {
		Main.genNewNumbers();
		changeLabels();
		clearFields();

		addFld.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if ((addFld.getText() != null) && (addFld.getText().length() > 0) && newValue.compareTo(".") != 0
						&& newValue.compareTo("-") != 0 && Main.answers.contains(Double.parseDouble(newValue))) {
					Main.resBool[0] = true;
					addFld.setDisable(true);
				} else {
					Main.resBool[0] = false;
				}

				update();
			}
		});

		subFld.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if ((subFld.getText() != null) && (subFld.getText().length() > 0) && newValue.compareTo(".") != 0
						&& newValue.compareTo("-") != 0 && (Main.answers.contains(Double.parseDouble(newValue)))) {
					Main.resBool[1] = true;
					subFld.setDisable(true);
				} else {
					Main.resBool[1] = false;
				}

				update();
			}
		});

		mulFld.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if ((mulFld.getText() != null) && (mulFld.getText().length() > 0) && newValue.compareTo(".") != 0
						&& newValue.compareTo("-") != 0 && (Main.answers.contains(Double.parseDouble(newValue)))) {
					Main.resBool[2] = true;
					mulFld.setDisable(true);
				} else {
					Main.resBool[2] = false;
				}

				update();
			}
		});

		divFld.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if ((divFld.getText() != null) && (divFld.getText().length() > 0) && newValue.compareTo(".") != 0
						&& newValue.compareTo("-") != 0 && (Main.answers.contains(Double.parseDouble(newValue)))) {
					Main.resBool[3] = true;
					divFld.setDisable(true);
				} else {
					Main.resBool[3] = false;
				}

				update();
			}
		});
	}

	@FXML
	void newQuiz() {
		Main.genNewNumbers();
		changeLabels();
		clearFields();
	}

	void changeLabels() {
		titleLabel.setText(String.format("Two randomly generated numbers are: %.2f and %.2f", Main.x, Main.y));
		addLabel.setText(String.format("%.2f + %.2f = ", Main.x, Main.y));
		subLabel.setText(String.format("%.2f - %.2f = ", Main.x, Main.y));
		mulLabel.setText(String.format("%.2f * %.2f = ", Main.x, Main.y));
		divLabel.setText(String.format("%.2f / %.2f = ", Main.x, Main.y));

		correctLabel.setText("Number of correct answers: ");
		incorrectLabel.setText("Number of incorrect answers: ");
	}

	void update() {
		Main.count = 0;
		for (boolean b : Main.resBool) {
			Main.count = b ? Main.count + 1 : Main.count;
		}

		correctLabel.setText("Number of correct answers: " + Main.count);
		incorrectLabel.setText("Number of incorrect answers: " + (4 - Main.count));
	}

	void clearFields() {
		addFld.setDisable(false);
		subFld.setDisable(false);
		mulFld.setDisable(false);
		divFld.setDisable(false);

		addFld.setText(null);
		subFld.setText(null);
		divFld.setText(null);
		mulFld.setText(null);

	}

}
