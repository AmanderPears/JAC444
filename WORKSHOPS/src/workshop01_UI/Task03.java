package workshop01_UI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Task03 extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagLayout grid = new GridBagLayout();
	private GridBagConstraints gridc = new GridBagConstraints();

	private static DecimalFormat money = new DecimalFormat("$###,###,##0.00");
	private NumberFormat mask;
	private JFormattedTextField amtftf, periodftf, rateftf;
	private Vector<JFormattedTextField> ftfList = new Vector<JFormattedTextField>();

	private int period;
	private double principal, rate, total, amt;
	private JLabel monthlylbl, totallbl;

	// table stuff
	private JScrollPane scrollPane = new JScrollPane();
	private String[] columnNames = { "Payment #", "Interest", "Principle", "Balance" };
	private Object[][] data = new Object[0][0];

	// cheat
	private JPanel mainPanel;

	public Task03() {
		mainPanel = this;
		// setup layout manager
		this.setLayout(grid);

		// setup mask
		mask = NumberFormat.getNumberInstance();
		mask.setGroupingUsed(false);

		// gridc.weighty = 1;
		gridc.weightx = 1;
		// gridc.anchor = GridBagConstraints.WEST;
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		gridc.fill = GridBagConstraints.BOTH;
		// info
		JLabel title = new JLabel();
		Font boldFont = new Font(title.getFont().getFontName(), Font.BOLD, title.getFont().getSize());
		title.setFont(boldFont);
		createlbl(title, "Loan Amortization Calculator", this);
		title.setHorizontalAlignment(JTextField.CENTER);

		gridc.fill = GridBagConstraints.HORIZONTAL;
		// row1
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		createlbl(new JLabel(), "Principal ↴", this);

		// row2
		amtftf = new JFormattedTextField(mask);
		gridc.gridwidth = 1;
		createftf(amtftf, this);

		gridc.gridwidth = GridBagConstraints.REMAINDER;
		createlbl(new JLabel(), "Monthly Payment ↴", this);

		// row3
		gridc.gridwidth = 1;
		createlbl(new JLabel(), "Period ↴", this);

		gridc.gridwidth = GridBagConstraints.REMAINDER;
		monthlylbl = new JLabel();
		createlbl(monthlylbl, "$ 00.00", this);
		monthlylbl.setHorizontalAlignment(JTextField.RIGHT);

		// row4
		periodftf = new JFormattedTextField(mask);
		gridc.gridwidth = 1;
		createftf(periodftf, this);

		gridc.gridwidth = GridBagConstraints.REMAINDER;
		createlbl(new JLabel(), "Total Payment ↴", this);

		// row5
		gridc.gridwidth = 1;
		createlbl(new JLabel(), "Annual Rate ↴", this);

		gridc.gridwidth = GridBagConstraints.REMAINDER;
		totallbl = new JLabel();
		createlbl(totallbl, "$ 00.00", this);
		totallbl.setHorizontalAlignment(JTextField.RIGHT);

		// row6
		rateftf = new JFormattedTextField(mask);
		gridc.gridwidth = 1;
		createftf(rateftf, this);

		JButton button = new JButton("Calculate");
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(button, gridc);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				calc();
			}
		});

		// listeners
		for (JFormattedTextField j : ftfList) {
			j.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					// Select all text in the formatted text field on focus
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							// wait for stuff before calling the function so it does something
							j.selectAll();
						}
					});
				}
			});
		}

		// space row
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		createlbl(new JLabel(), "", this);

		// display table
		// mainPanel.remove(scrollPane);
		scrollPane = new JScrollPane();
		gridc.weighty = 1;
		gridc.gridheight++;
		gridc.fill = GridBagConstraints.BOTH;
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(scrollPane, gridc);

	}

	public void createlbl(JLabel lb, String text, JPanel p) {
		lb.setText(text);
		lb.setHorizontalAlignment(SwingConstants.LEFT);
		p.add(lb, gridc);
	}

	public void createftf(JFormattedTextField ftf, JPanel p) {
		ftf.setColumns(10);
		ftf.setHorizontalAlignment(JTextField.RIGHT);
		// gridc.fill = GridBagConstraints.NONE;
		// gridc.weightx = 0.7;
		p.add(ftf, gridc);
		// gridc.weightx = 1;
		// gridc.fill = GridBagConstraints.HORIZONTAL;
		ftfList.addElement(ftf);
	}

	public void calc() {
		principal = amtftf.getValue() != null ? ((Number) amtftf.getValue()).doubleValue() : 0;
		period = periodftf.getValue() != null ? ((Number) periodftf.getValue()).intValue() : 0;
		rate = rateftf.getValue() != null ? ((Number) rateftf.getValue()).doubleValue() : 0;

		if (principal > 0 && period > 0 && rate > 0) {
			period *= 12;
			rate = (rate / 100) / 12;

			amt = principal * ((rate * Math.pow(1 + rate, period)) / (Math.pow(1 + rate, period) - 1));
			total = amt * period;

			monthlylbl.setText(money.format(amt));
			totallbl.setText(money.format(total));

			createTable();
		}
	}

	public void createTable() {
		data = new Object[period][4];
		for (int i = 0; i < period; i++) {
			data[i][0] = i + 1;
			data[i][1] = money.format(rate * principal);
			data[i][2] = money.format(amt - (rate * principal));
			principal -= (amt - (rate * principal));
			data[i][3] = money.format(principal);
		}

		mainPanel.remove(scrollPane);
		scrollPane = new JScrollPane(new JTable(data, columnNames));
		gridc.weighty = 1;
		gridc.gridheight++;
		gridc.fill = GridBagConstraints.BOTH;
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		mainPanel.add(scrollPane, gridc);

	}

}
