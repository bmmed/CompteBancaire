package fenetre;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class FenetreDiagramme extends JFrame{

	private JPanel contentPane;
public static void main (String args[]){

	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				FenetreDiagramme frame = new FenetreDiagramme(ops);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});

	}
	 static ArrayList<Graph> ops ;
	public FenetreDiagramme(ArrayList<Graph> ops) {
		this.ops=ops ;
		
		pane panel = new pane(ops);
		panel.setBounds(10, 11, 951, 681);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 998, 742);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	    contentPane.setLayout(null);
	    contentPane.add(panel);
	    panel.setLayout(null);
	    
	    JLabel lblSolde = new JLabel("solde");
	    lblSolde.setBounds(10, 51, 25, 14);
	    panel.add(lblSolde);
	    
	    JLabel lblDateDoperation = new JLabel("date d'operation");
	    lblDateDoperation.setBounds(803, 656, 79, 14);
	    panel.add(lblDateDoperation);
	   // contentPane.setVisible(true);

	}
}