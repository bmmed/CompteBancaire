package fenetre;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.*;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class FenetreGestionCompte extends JFrame {
	
	private OpsModel model ; 
	private JPanel contentPane;
	private Date d1=MaDate.getMaDate(2017,1,1); 
	private Date d2=MaDate.getMaDate(2017,4,1); 
	JComboBox<String> choixCpt,modifCat ;
	private JComboBox j1,j2,m1,m2 ;
	private char str ;
	 static Client c1 ;
	 static ArrayList<Operation> opes = new ArrayList<Operation>() ;
	 static ArrayList<Operation> ops = new ArrayList<Operation>() ;
	 static ArrayList<Graph> statist = new ArrayList<Graph>() ;
	 private JCheckBox chckbxNewCheckBox,chckbxNewCheckBox_1 ;
	private static Metier m =new Metier(); 
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					FenetreGestionCompte frame = new FenetreGestionCompte(c1);
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
	public FenetreGestionCompte(Client c) {
		
		c1= c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 642);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		contentPane.setLayout(null);
		String[] mois ={"01","02","03","04","05","06","07","08","09","10","11","12"} ;
		String[] jours ={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
			model= new OpsModel(ops) ;
			JPanel panel = new JPanel();
			panel.setBounds(10, 223, 838, 369);
			panel.setLayout(null);
			table = new JTable(model);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setBounds(10, 210, 641, 382);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 0, 818, 358);
			panel.add(scrollPane);
			contentPane.add(panel);
			
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(10, 11, 345, 196);
			contentPane.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblChoisiroperation = new JLabel("choisir operation");
			lblChoisiroperation.setBounds(10, 119, 79, 14);
			panel_1.add(lblChoisiroperation);
			
			 chckbxNewCheckBox = new JCheckBox("revenu");
			 chckbxNewCheckBox.setBounds(10, 140, 79, 23);
			 panel_1.add(chckbxNewCheckBox);
			 chckbxNewCheckBox.setSelected(true);
			 chckbxNewCheckBox.setVerticalAlignment(SwingConstants.TOP);
			 
			   chckbxNewCheckBox_1 = new JCheckBox("depense");
			   chckbxNewCheckBox_1.setBounds(10, 166, 79, 23);
			   panel_1.add(chckbxNewCheckBox_1);
			   chckbxNewCheckBox_1.setSelected(true);
			   
			   JLabel lblSelectDateDebut = new JLabel("select date debut et fin :");
			   lblSelectDateDebut.setBounds(145, 119, 150, 14);
			   panel_1.add(lblSelectDateDebut);
			   
			   JLabel lblJj = new JLabel("j1");
			   lblJj.setBounds(118, 144, 16, 14);
			   panel_1.add(lblJj);
			   
			   JLabel label_1 = new JLabel("j2");
			   label_1.setBounds(229, 144, 16, 14);
			   panel_1.add(label_1);
			   
			   JLabel label_2 = new JLabel("m1");
			   label_2.setBounds(118, 170, 22, 14);
			   panel_1.add(label_2);
			   
			   JLabel label_3 = new JLabel("m2");
			   label_3.setBounds(229, 170, 29, 14);
			   panel_1.add(label_3);
			   
			    m1 = new JComboBox(jours);
			    m1.setBounds(154, 167, 40, 20);
			    panel_1.add(m1);
			    
			     m2 = new JComboBox(mois);
			     m2.setBounds(267, 167, 40, 20);
			     panel_1.add(m2);
			     m2.setSelectedIndex(3);
			     
			      j1 = new JComboBox(jours);
			      j1.setBounds(155, 141, 40, 20);
			      panel_1.add(j1);
			      
			       j2 = new JComboBox(mois);
			       j2.setBounds(267, 141, 40, 20);
			       panel_1.add(j2);
			       
			       
			        choixCpt = new JComboBox<String>();
			        choixCpt.setBounds(10, 47, 126, 20);
			        panel_1.add(choixCpt);
			        
			        
			        
			        JLabel lblCompte = new JLabel("Compte");
			        lblCompte.setBounds(38, 11, 46, 14);
			        panel_1.add(lblCompte);
			        
			        JButton afficheTab = new JButton("afficher tab");
			        afficheTab.setBounds(201, 11, 109, 23);
			        panel_1.add(afficheTab);
			        
			        JButton btnAffichageGraph = new JButton("affichage graph");
			        btnAffichageGraph.setBounds(201, 35, 109, 23);
			        panel_1.add(btnAffichageGraph);
			        
			        
			        btnAffichageGraph.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent arg0) {
			        		
			        		FenetreDiagramme fs = new FenetreDiagramme(statist);
			        	fs.setVisible(true);
			        	}
			        });
			        afficheTab.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent arg0) {
			        		System.out.println(choixCpt.getSelectedItem());
			        		
			        		//try 
			        		{
			        			if(chckbxNewCheckBox_1.isSelected() && !chckbxNewCheckBox.isSelected() ){
			        				str ='d' ;
			        			}else if(!chckbxNewCheckBox_1.isSelected() && chckbxNewCheckBox.isSelected()){
			        				str='r';
			        			}else if(chckbxNewCheckBox_1.isSelected() && chckbxNewCheckBox.isSelected()){
			        				str='a' ;
			        			}
			        			
			        			if(choixCpt.getSelectedIndex()==0){ 
			        				
			        				ops.removeAll(ops);	
			        				for(Operation o : m.getOpsClient(c1, str)){
			        				Graph gr= new Graph() ;
			        				if(o.getDateOps().getTime()> d1.getTime() && o.getDateOps().getTime()<d2.getTime()){
			        				ops.add(o) ;	
			        				gr.setId(o.getFkCpt());
			        				gr.setDt(o.getDateOps());
			        				gr.setSolde(o.getSoldeApres());
			        				statist.add(gr) ; 
			        				
			        				
			        					}
			        					
			        				}
			        				
			        				model.setData(ops);
			        			}else{
			        				Compte cpt=null ;
			        				for (Compte c:c1.getCptClient()){
			        					if(c.getIdCpt()==choixCpt.getSelectedIndex())
			        						cpt=c;
			        				}
			        				ops.removeAll(ops);	
			        				Graph gr= new Graph() ;
			        				for(Operation o : m.getOpsCpt(cpt, str)){
			        					
			        					if(o.getDateOps().getTime()> d1.getTime() && o.getDateOps().getTime()<d2.getTime()){
			        					ops.add(o) ;	
			        					gr.setId(o.getFkCpt());
			        					gr.setDt(o.getDateOps());
			        					gr.setSolde(o.getSoldeApres());
			        					statist.add(gr) ; 
			        					
			        				
			        					}
			        					
			        				}
			        				
			        				model.setData(ops);
			        			}
			        		}
			        		
			        	}
			        });
			        choixCpt.addItem("tout");
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(389, 11, 214, 88);
			contentPane.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblPrenom = new JLabel("PRENOM:");
			lblPrenom.setBounds(10, 50, 127, 14);
			panel_2.add(lblPrenom);
			lblPrenom.setText(lblPrenom.getText()+c1.getPrenomClient());
			
			JLabel lblNom = new JLabel("NOM :  ");
			lblNom.setBounds(10, 11, 127, 14);
			panel_2.add(lblNom);
			lblNom.setText(lblNom.getText()+c1.getNomClient());
			
			JLabel lblSoldeTotal = new JLabel("Solde total :");
			lblSoldeTotal.setBounds(136, 11, 78, 14);
			panel_2.add(lblSoldeTotal);
			
			JLabel label = new JLabel(String.valueOf(m.getSoldeTotal(c1)));
			label.setBounds(151, 50, 53, 14);
			panel_2.add(label);
			for(Compte i:m.getCpt(c1)){
				choixCpt.addItem(i.getDesCpt());
				System.out.println(i.getDesCpt());
			}
			
			JPanel panel_3 = new JPanel();
			panel_3.setLayout(null);
			panel_3.setBounds(389, 124, 214, 88);
			contentPane.add(panel_3);
			
			modifCat = new JComboBox<String>();
			modifCat.setBounds(10, 21, 194, 23);
			panel_3.add(modifCat);
			modifCat.addItem("tout");
			for(Categorie i:m.getCatClient(c1)){
				modifCat.addItem(i.getDesCat());
			}
			JLabel lblChoisirCategorie = new JLabel("modifier categorie");
			lblChoisirCategorie.setBounds(55, 0, 91, 14);
			panel_3.add(lblChoisirCategorie);
			
			JButton update = new JButton("update cat");
			update.setBounds(55, 55, 109, 20);
			panel_3.add(update);
			
			JPanel panel_4 = new JPanel();
			panel_4.setLayout(null);
			panel_4.setBounds(618, 11, 214, 88);
			contentPane.add(panel_4);
			
			JComboBox<String> comboBox = new JComboBox<String>();

			comboBox.setBounds(10, 25, 194, 23);
			panel_4.add(comboBox);
			comboBox.addItem("");
			for(Categorie i:m.getCatClient(c1)){
				comboBox.addItem(i.getDesCat());
			}
						comboBox.setSelectedIndex(1);
			JLabel lblAfficherCategorie = new JLabel("afficher categorie");
			lblAfficherCategorie.setBounds(64, 0, 116, 14);
			panel_4.add(lblAfficherCategorie);
			
			JButton btnAfficherCat = new JButton("afficher cat");
			/*btnAfficherCat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {


					for(Operation o : m.getOpsCat(comboBox.getSelectedIndex())){
    					
    					opes.add(o) ;
    					
    					}
    				model.setData(opes);
    			}
			});
			*/
			btnAfficherCat.setBounds(55, 55, 109, 20);
			panel_4.add(btnAfficherCat);
			
			
			update.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int selection = table.getSelectedRow();
						 
					ops.get(selection).setFkCat(modifCat.getSelectedIndex());
					m.updateOps(ops.get(selection));
					afficheTab.doClick();
				}
			});
			new SimpleDateFormat("dd/MM/yyyy");
        
	}
	
	
	public char getops(){
		return str ; 
	
	}
}
