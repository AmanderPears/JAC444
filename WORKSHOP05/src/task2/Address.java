package task2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Address implements Initializable {

	private static int recordLength = 76;

	private static String fName, lName, city, postal, prov;

	private static RandomAccessFile RAFfile;

	@FXML
	private TextField firstNameField, lastNameField, cityField, postalField;

	@FXML
	private ChoiceBox<String> provinceList;

	@FXML
	private Label statusLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// set dropdown items
		provinceList.setItems(
				FXCollections.observableArrayList("ON", "QC", "NS", "NB", "MB", "BC", "PE", "SK", "AB", "NL"));

		// intialize RAF
		try {
			RAFfile = new RandomAccessFile("address.txt", "rw");
			RAFfile.setLength(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void setStatus(String s) {
		statusLabel.setText((new Date()) + "--->" + s.toUpperCase());
	}

	@FXML
	void addAddress() throws Exception {
		// if (RAFfile.length() != 0) {
		RAFfile.seek(RAFfile.length());
		fName = fixString(firstNameField.getText());
		lName = fixString(lastNameField.getText());
		city = fixString(cityField.getText());
		postal = fixString(postalField.getText());
		prov = fixString(provinceList.getValue());

		String temp = fName + lName + city + prov + postal;
		if (temp.length() > 0) {
			RAFfile.writeBytes(temp + '\n');
			setStatus("Address added to end of file");
		}
		// }
	}

	@FXML
	void updateAdd() throws Exception {
		if (RAFfile.length() != 0) {
			long currentPos = RAFfile.getFilePointer();

			fName = fixString(firstNameField.getText());
			lName = fixString(lastNameField.getText());
			city = fixString(cityField.getText());
			postal = fixString(postalField.getText());
			prov = fixString(provinceList.getValue());

			RAFfile.writeBytes(fName + lName + city + prov + postal + '\n');
			RAFfile.seek(currentPos);

			setStatus("Address updated");
		}
	}

	// shorten string if too long and fill if too short
	String fixString(String s) {
		if (s != null && !s.isEmpty()) {
			if (s.length() > 15) {
				s = s.substring(0, 15);
			}

			return String.format("%15s", s);
		}

		return "";
	}

	@FXML
	void firstAdd() throws Exception {
		if (RAFfile.length() != 0) {
			seekAdd(0);
		}
	}

	@FXML
	void lastAdd() throws Exception {
		if (RAFfile.length() != 0) {
			seekAdd(RAFfile.length() - recordLength);
		}
	}

	@FXML
	void nextAdd() throws Exception {
		// if file not empty
		if (RAFfile.length() != 0) {
			if (RAFfile.getFilePointer() == (RAFfile.length() - recordLength)) {
				seekAdd(RAFfile.length() - recordLength);
			} else {
				seekAdd(RAFfile.getFilePointer() + recordLength);
			}
		}
	}

	@FXML
	void prevAdd() throws Exception {
		if (RAFfile.length() != 0) {
			if ((RAFfile.getFilePointer() - recordLength) < 0)
				seekAdd(0);
			else
				seekAdd(RAFfile.getFilePointer() - recordLength);
		}
	}

	void seekAdd(long i) {
		try {
			// seek to the position
			RAFfile.seek(i);

			// read the line and display data
			setFields(RAFfile.readLine());

			// set seek to previous pos
			RAFfile.seek(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error in *seekAdd*");
		}
	}

	void setFields(String s) {
		firstNameField.setText(returnField(s, 0));
		lastNameField.setText(returnField(s, 1));
		cityField.setText(returnField(s, 2));
		provinceList.setValue(returnField(s, 3));
		postalField.setText(returnField(s, 4));
	}

	String returnField(String s, int pos) {
		return s.substring(15 * pos, 15 * pos + 15).trim();
	}

//	@FXML
//	void prevAdd() {
//		try {
//			long lineLength = 151;
//			long currentPos = RAFfile.getFilePointer();
//			long prevPos = lineLength * 2;
//			System.out.println("current: " + currentPos);
//
//			RAFfile.seek((currentPos - prevPos) < 0 ? 0 : (currentPos - prevPos));
//
//			currentPos = RAFfile.getFilePointer();
//			System.out.println(currentPos);
//
//			String line = RAFfile.readLine();
//			String[] fields = new String[5];
//			int i = 0;
//			for (String s : line.split(" ")) {
//				if ((s.trim()).length() > 0) {
//					// System.out.println(s);
//					fields[i++] = s;
//				}
//			}
//
//			firstNameField.setText(fields[0]);
//			lastNameField.setText(fields[1]);
//			cityField.setText(fields[2]);
//			provinceList.setValue(fields[3]);
//			postalField.setText(fields[4]);
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public static RandomAccessFile RAFfile;
//
//	private static StringBuilder strbdr = new StringBuilder();
//
//	public Address() {
//		try {
//			RAFfile = new RandomAccessFile("address.txt", "rw");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public static void set_address(String f, String l, String c, String p, String pos) {
//		// clear strbdr
//		if (strbdr.length() < 0)
//			strbdr.delete(0, strbdr.length());
//
//		strbdr.append(f).append(",").append(l).append(",").append(c).append(",").append(p).append(",").append(pos);
//
//		try {
//			RAFfile.writeChars(strbdr.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}