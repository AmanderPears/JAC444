package workshop01_UI;

import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class Task02_NEW extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private GridBagConstraints gridc = new GridBagConstraints();
	public static String[] days = { "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };

	public Task02_NEW() throws ParseException {
		// cheat
		mainPanel = this;
		mainPanel.setLayout(new GridBagLayout());

		//
		gridc.fill = GridBagConstraints.HORIZONTAL;

		// date input
		JLabel inputlbl = new JLabel("Enter date: (dd-mm-yyyy): ");
		gridc.anchor = GridBagConstraints.WEST;
		gridc.gridwidth = 1;
		mainPanel.add(inputlbl, gridc);

		MaskFormatter mask = new MaskFormatter("##-##-####");
		mask.setPlaceholderCharacter('_');
		JFormattedTextField inputftf = new JFormattedTextField(mask);
		inputftf.setColumns(10);
		inputftf.setHorizontalAlignment(JTextField.CENTER);
		inputftf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent evt) {
				inputftf.setValue(null);
			}
		});
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		mainPanel.add(inputftf, gridc);

		// Jbu
		JButton button = new JButton("What day was it!?");
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		mainPanel.add(button, gridc);

		// temp result
		JLabel res = new JLabel("Please enter a date ↗");
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		mainPanel.add(res, gridc);

		// test
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				calc(inputftf, res);
			}
		});

	}

	public void calc(JFormattedTextField ftf, JLabel lbl) {

		if (ftf.getValue() != null) {
			String date = ftf.getValue().toString();

			int day = Integer.parseInt(ftf.getValue().toString().substring(0, 2));
			int month = Integer.parseInt(ftf.getValue().toString().substring(3, 5));
			int year = Integer.parseInt(ftf.getValue().toString().substring(6));

			// leapyear
			int feb = 0;
			if (year % 4 != 0)
				feb = 28;
			else if (year % 100 != 0)
				feb = 29;
			else if (year % 400 != 0)
				feb = 28;
			else
				feb = 29;

			int[] daysInMonth = { 0, 31, feb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

			if (day >= 1 && day <= 31 && month >= 1 && month <= 12) {
				if (day <= daysInMonth[month]) {

					if (month == 1) {
						month = 13;
						year--;
					} else if (month == 2) {
						month = 14;
						year--;
					}

					int h = (day + ((26 * (month + 1)) / 10) + (year % 100) + ((year % 100) / 4) + ((year / 100) / 4)
							+ (5 * (year / 100))) % 7;
					lbl.setText(date + " is a " + days[h]);

				} else {
					lbl.setText(date + " is invalid!");
				}
			} else {
				lbl.setText(date + " is invalid!");
			}
		}

		/*
		 * months with 31: 1,3,5,7,8,10,12 months with 30: 4,6,9,11 feb....
		 */
	}

}
