package workshop01_UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Task01 extends JPanel implements PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static GridBagLayout grid = new GridBagLayout();
	private static GridBagConstraints gridc = new GridBagConstraints();

	// private static ImageIcon image = new ImageIcon("resource/image/image.png");
	// private static URL imgURL =
	// private static ImageIcon image = new ImageIcon(imgURL);
	private static JLabel result_lbl = new JLabel();

	private static NumberFormat mask;
	private static DecimalFormat resultMask = new DecimalFormat("###,###,##0.00");

	private static JFormattedTextField a_ftf, b_ftf, c_ftf, d_ftf, e_ftf, f_ftf;

	// listener fix
	private static Vector<JFormattedTextField> list = new Vector<JFormattedTextField>();

	//
	private static double x = 0.0, y = 0.0, a = 0.0, b = 0.0, c = 0.0, d = 0.0, e = 0.0, f = 0.0;

	// constructor
	public Task01() throws ParseException {
		// setup layout manager
		// grid = new GridBagLayout();
		// gridc = new GridBagConstraints();
		this.setLayout(grid);

		gridc.weighty = 1;
		gridc.fill = GridBagConstraints.BOTH;
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		// display image
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon image = new ImageIcon(cl.getResource("image.png"));
		// ImageIcon image = new ImageIcon("resource/image/image.png");
		JLabel imageLabel = new JLabel(image);
		// grid.setConstraints(imageLabel, gridc);
		this.add(imageLabel, gridc);

		// setup textfields
		mask = NumberFormat.getNumberInstance();
		mask.setGroupingUsed(false);
		a_ftf = new JFormattedTextField(mask);
		b_ftf = new JFormattedTextField(mask);
		c_ftf = new JFormattedTextField(mask);
		d_ftf = new JFormattedTextField(mask);
		e_ftf = new JFormattedTextField(mask);
		f_ftf = new JFormattedTextField(mask);

		// set up grid
		gridc.fill = GridBagConstraints.HORIZONTAL;
		gridc.weightx = 1;
		gridc.gridwidth = 1;

		// 2nd row
		createLabel(new JLabel(), "a =", this);
		createftf(a_ftf, this, a);

		createLabel(new JLabel(), "b =", this);
		createftf(b_ftf, this, b);

		createLabel(new JLabel(), "c =", this);
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		createftf(c_ftf, this, c);

		// 3rd row
		gridc.gridwidth = 1;
		createLabel(new JLabel(), "d =", this);
		createftf(d_ftf, this, d);

		createLabel(new JLabel(), "e =", this);
		createftf(e_ftf, this, e);

		createLabel(new JLabel(), "f =", this);
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		createftf(f_ftf, this, f);

		gridc.gridwidth = 4;
		createLabel(result_lbl, "x = ?, y = ?", this);

		// listen for changes in text field
		for (JFormattedTextField i : list) {
			i.addPropertyChangeListener("value", this);
			i.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					i.setValue(null);
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}

		gridc.gridwidth = 1;
		createLabel(new JLabel(), "", this);
		// button
		gridc.gridwidth = GridBagConstraints.REMAINDER;
		JButton calcbutton = new JButton("Calculate");
		calcbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				calc();
			}

		});
		this.add(calcbutton, gridc);
	}

	public static void createLabel(JLabel lb, String text, JPanel p) {
		// lb = new JLabel(text);
		lb.setText(text);
		lb.setHorizontalAlignment(SwingConstants.RIGHT);
		p.add(lb, gridc);
	}

	public static void createftf(JFormattedTextField tf, JPanel p, double d) {
		tf.setValue(d);
		tf.setHorizontalAlignment(JTextField.CENTER);
		tf.setColumns(7);
		// tf.set
		// grid.setConstraints(tf, gridc);
		p.add(tf, gridc);
		//
		list.addElement(tf);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		calc();
	}

	public static void calc() {

		a = a_ftf.getValue() != null ? ((Number) a_ftf.getValue()).doubleValue() : 0;
		b = b_ftf.getValue() != null ? ((Number) b_ftf.getValue()).doubleValue() : 0;
		c = c_ftf.getValue() != null ? ((Number) c_ftf.getValue()).doubleValue() : 0;
		d = d_ftf.getValue() != null ? ((Number) d_ftf.getValue()).doubleValue() : 0;
		e = e_ftf.getValue() != null ? ((Number) e_ftf.getValue()).doubleValue() : 0;
		f = f_ftf.getValue() != null ? ((Number) f_ftf.getValue()).doubleValue() : 0;

		if ((a * d - b * c) == 0) {
			result_lbl.setText("No Solution (ad-bc = 0)");
		} else {
			x = (e * d - b * f) / (a * d - b * c);
			y = (a * f - e * c) / (a * d - b * c);

			result_lbl.setText("x = " + resultMask.format(x) + ", y = " + resultMask.format(y));
		}
	}
}
