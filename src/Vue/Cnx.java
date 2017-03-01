package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;

public class Cnx {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cnx window = new Cnx();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cnx() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		passwordField = new JPasswordField();

		passwordField.setColumns(15);
		
		textField = new JTextField();
		textField.setColumns(15);
		panel.setLayout(new MigLayout("", "[126px][126px]", "[20px]"));
		panel.add(passwordField, "cell 0 0,alignx left,aligny top");
		panel.add(textField, "cell 1 0,alignx left,aligny top");
	}

}
