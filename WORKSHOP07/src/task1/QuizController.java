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

				checkFld(addFld, 0, newValue);
/*
 * old ugly and buggy method for checking below
 */
//				if ((addFld.getText() != null) && (addFld.getText().length() > 0) && newValue.compareTo(".") != 0
//						&& newValue.compareTo("-") != 0 && Main.answers.contains(Double.parseDouble(newValue))) {
//					Main.resBool[0] = true;
//					addFld.setDisable(true);
//				} else {
//					Main.resBool[0] = false;
//				}

//				update();
			}
		});

		subFld.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				checkFld(subFld, 1, newValue);
			}
		});

		mulFld.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				checkFld(mulFld, 2, newValue);
			}
		});

		divFld.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				checkFld(divFld, 3, newValue);
			}
		});
	}

	void checkFld(TextField tf, int i, String s) {
		try {
			if (Main.answers.contains(Double.parseDouble(s))) {
				Main.resBool[i] = true;
				tf.setDisable(true);
			}
		} catch (Exception e) {
			Main.resBool[i] = false;
		}

		update();
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
