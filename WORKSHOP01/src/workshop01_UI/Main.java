package workshop01_UI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Main {

	public static void createAndShowGUI() {
		//following javase tutorial
		//create and setup components
		JFrame frame = new JFrame("Workshop 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600,400));

		//use JPanel to house the tabbed panels
		JPanel panel = new JPanel();
		//set layout for panel
		panel.setLayout(new GridLayout(1,1));

		//use tabbed pane
		JTabbedPane tabbedPane = new JTabbedPane();
		//set tabbedpanel layout
		tabbedPane.setLayout(new GridLayout(1,1));

		//add panels for each workshop task
		//task 1
		JPanel task1Panel = new JPanel(false);
		task1Panel.setLayout(new GridLayout(1,1));
		try {
			task1Panel.add(new Task01_NEW());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//task 2
		JPanel task2Panel = new JPanel(false);
		task2Panel.setLayout(new GridLayout(1,1));
		try {
			task2Panel.add(new Task02_NEW());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//task 3
		JPanel task3Panel = new JPanel(false);
		task3Panel.setLayout(new GridLayout(1,1));
		task3Panel.add(new Task03());


		//tabbedpane setting
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		//annoying process of composition
		tabbedPane.addTab("Task 1", task1Panel);
		tabbedPane.addTab("Task 2", task2Panel);
		tabbedPane.addTab("Task 3", task3Panel);
		panel.add(tabbedPane);
		frame.add(panel);

		//display the window
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
