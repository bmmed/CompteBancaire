package fenetre;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Controller.*;

	public class OpsModel extends AbstractTableModel  {
		ArrayList<String[]> tablevalues ;
		ArrayList<Operation> ops = new ArrayList<Operation>();
		private final String[] entetes = { "idOps", "fkCpt", "fkCat", "desTiers", "montantOps", "typeOps", "soldeAvant", "soldeApres", "dateOps" };	
		
		public int getRowCount() {
			return ops.size();
		}
		
		
		public  OpsModel(ArrayList<Operation> ops) {
			this.ops=ops ;
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			return tablevalues.get(rowIndex)[columnIndex];
		}
		public void setData(ArrayList<Operation> ops){
			tablevalues=new ArrayList<String[]>();
			
			for (Operation o:ops){
				tablevalues.add(new String[]{
					String.valueOf(o.getIdOps()),
					String.valueOf(o.getFkCpt()),
					String.valueOf(o.getFkCat()),
					String.valueOf(o.getDesTiers()),
					String.valueOf(o.getMontantOps()),
					String.valueOf(o.getSoldeAvant()),
					String.valueOf(o.getSoldeApres()),
					String.valueOf(o.getTypeOps()),
					String.valueOf(o.getDateOps())
					
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
	