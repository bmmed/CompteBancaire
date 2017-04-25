package fenetre;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import code.Metier;
import code.Client;
import  code.Echeance;
import javax.swing.JLabel;
import java.awt.Font;

public class FenetreEch extends JFrame {

	private JPanel contentPane;
	private EchModel model ; 
	private JTable table;
	static ArrayList<Echeance> ops = new ArrayList<Echeance>() ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					FenetreEch frame = new FenetreEch(ops);
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
	public FenetreEch(ArrayList<Echeance> Ech) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ops.addAll(Ech);
		
		model= new EchModel(ops) ;
		
		model.setData(ops);
		JPanel panel = new JPanel();
		panel.setBounds(10, 157, 738, 326);
		panel.setLayout(null);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(10, 210, 641, 382);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 0, 718, 315);
		panel.add(scrollPane);
		contentPane.add(panel);
		
		JLabel lblAffichageDeTous = new JLabel("Affichage de tous les echeances");
		lblAffichageDeTous.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAffichageDeTous.setBounds(253, 51, 265, 45);
		contentPane.add(lblAffichageDeTous);
	}
}
