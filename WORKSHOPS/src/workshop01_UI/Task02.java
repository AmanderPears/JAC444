package workshop01_UI;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class Task02 extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String[] days = { "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
	public int year = 0, month = 0, day = 0, h;
	public GridLayout grid;
	public JLabel yearLabel, monthLabel, dayLabel, result;
	public JFormattedTextField yearText, monthText, dayText;
	public NumberFormat yearFormat, monthFormat, dayFormat;
	public JButton resultButton;

	public Task02() {
		grid = new GridLayout(4, 2);
		this.setLayout(grid);

		// year
		yearLabel = new JLabel();
		yearLabel.setText("Enter year");
		this.add(yearLabel);

		yearFormat = NumberFormat.getIntegerInstance();
		yearFormat.setGroupingUsed(false);
		yearText = new JFormattedTextField(yearFormat);
		yearText.setValue(year);
		yearText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				yearText.setText("");
			}
		});
		this.add(yearText);

		// month
		monthLabel = new JLabel();
		monthLabel.setText("Enter month");
		this.add(monthLabel);

		monthFormat = NumberFormat.getIntegerInstance();
		monthFormat.setMaximumIntegerDigits(2);
		monthText = new JFormattedTextField(monthFormat);
		monthText.setValue(month);
		monthText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				monthText.setText("");
			}
		});
		this.add(monthText);

		// day
		dayLabel = new JLabel();
		dayLabel.setText("Enter the day\nof the month");
		this.add(dayLabel);

		dayFormat = NumberFormat.getIntegerInstance();
		dayFormat.setMaximumIntegerDigits(2);
		dayText = new JFormattedTextField(dayFormat);
		dayText.setValue(day);
		dayText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dayText.setText("");
			}
		});
		this.add(dayText);

		// button
		resultButton = new JButton("What day was it?");
		resultButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WhatDayWasIt();
			}
		});
		this.add(resultButton);

		// result
		result = new JLabel();
		result.setText("...");
		this.add(result);

	}

	public void WhatDayWasIt() {
		year = ((Number) yearText.getValue()).intValue();
		month = ((Number) monthText.getValue()).intValue();
		day = ((Number) dayText.getValue()).intValue();

		if (month == 1) {
			month = 13;
			year--;
		} else if (month == 2) {
			month = 14;
			year--;
		}
		h = (day + ((26 * (month + 1)) / 10) + (year % 100) + ((year % 100) / 4) + ((year / 100) / 4)
				+ (5 * (year / 100))) % 7;
		result.setText(days[h]);
	}

}
