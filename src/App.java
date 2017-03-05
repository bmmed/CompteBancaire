import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class App {

	public static void main(String[] args) {

		Metier m =new Metier();

		m.testClient("client 01","0000");
		Client c1 = m.cnxClient("client 01","0000");
		m.executeEch(c1);



/*

		Compte cpt1 =c1.getCptClient().get(1);
		Date d1 =new Date(2005,11,12);
		Echeance ech01 =cpt1.creatEch(4,154.4,30,"ma designation de mone chance",d1);
		m.saveEch(ech01);
		m.refrechClient(c1);

		for(Compte i:c1.getCptClient()){
			System.out.println(i.getIdCpt()+" "+i.getDesCpt()+" "+i.getSolde());
		}
		Date d1 =new Date(2005,11,12);

		Compte cpt1 =c1.getCptClient().get(1);
		Operation ops1= cpt1.creatDepence(1,"freebox 01",45.21,d1);

		m.saveOps(ops1);
		m.upDateCpt(cpt1);
		Compte cpt1 =c1.getCptClient().get(0);

		for(Echeance i:cpt1.getListeEch()){

			System.out.println(i.getIdEch()+" "+i.getDes_ech()+" "+i.getMontant_ech()+" "+i.getFk_id_cpt_ech());
		}

		Categorie cat1 ;
		cat1 = c1.creatCat("ma nouvelmle categorie ");
		m.saveCat(cat1);
		m.refrechClient(c1);

*/






	}

}
