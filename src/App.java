import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

public class App {

	public static void main(String[] args) {

		Metier m =new Metier();

		m.testClient("client 01","0000");
		Client c1 = m.cnxClient("client 01","0000");
/*

		Date d1 =new Date(2005,11,12);

		Compte cpt1 =c1.getCptClient().get(1);
		Operation ops1= cpt1.creatDepence(1,"freebox 01",45.21,d1);

		m.saveOps(ops1);
		m.upDateCpt(cpt1);
*/


		Compte cpt1 =c1.getCptClient().get(0);

		for(Echeance i:cpt1.getListeEch()){

			System.out.println(i.getIdEch()+" "+i.getDes_ech()+" "+i.getMontant_ech()+" "+i.getFk_id_cpt_ech());
		}



	}

}
