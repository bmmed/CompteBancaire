package fenetre;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Controller.*;

	public class EchModel extends AbstractTableModel  {



		ArrayList<String[]> tablevalues ;
		ArrayList<Echeance> ops = new ArrayList<Echeance>();
		private final String[] entetes = { "idECH", "fkCpt", "fkCat", "montantEch", "periode_ech", "dessEch", "dateLastEch" };	
		
		public int getRowCount() {
			return ops.size();
		}
		
		
		public EchModel(ArrayList<Echeance> ops) {
			this.ops=ops ;// TODO Auto-generated constructor stub
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			
			return tablevalues.get(rowIndex)[columnIndex];
		}
		public void setData(ArrayList<Echeance> ops){
			tablevalues=new ArrayList<String[]>();
			
			for (Echeance o:ops){
				tablevalues.add(new String[]{
					String.valueOf(o.getIdEch()),
					String.valueOf(o.getFk_id_cpt_ech()),
					String.valueOf(o.getFk_id_cat_ech()),
					String.valueOf(o.getMontant_ech()),
					String.valueOf(o.getPeriode_ech()),
					String.valueOf(o.getDes_ech()),
					String.valueOf(o.getDate_last_ech())
					
				});
				fireTableChanged(null);
			}
			
			
		}
			
	
		@Override
		public int getColumnCount() {
			return entetes.length;
		}

		@Override
		public String getColumnName(int columnIndex) {
			return entetes[columnIndex];
		}
		}
	