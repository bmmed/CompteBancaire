package fenetre;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import code.Client;
import code.Metier;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Fcnx extends JFrame {

	private JPanel contentPane;
	private JTextField txtClient;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fcnx frame = new Fcnx();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Fcnx() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("connexion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login , mdp ; 
				login =  txtClient.getText() ;
				mdp= passwordField.getText() ;
				Metier m =  new Metier() ;
				if(m.testClient(login, mdp)){
					Client c1 = m.cnxClient(login,mdp);
					FenetrePrincipal Af = new FenetrePrincipal(c1) ; 
					Af.setVisible(true) ; 
						
				}else 
				;
				
			}
		});
		btnNewButton.setBounds(138, 156, 103, 31);
		contentPane.add(btnNewButton);
		
		txtClient = new JTextField();
		txtClient.setText("client 01");
		txtClient.setBounds(152, 53, 89, 20);
		contentPane.add(txtClient);
		txtClient.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(152, 105, 89, 20);
		contentPane.add(passwordField);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(171, 28, 70, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(170, 84, 71, 14);
		contentPane.add(lblPassword);
		
		
	}
}
