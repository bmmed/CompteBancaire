package Controller;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by BMMed on 28/02/2017.
 */
public interface IMetier {
    public boolean testClient(String u ,String mp);
    public Client cnxClient(String u ,String mp);
    public  void refrechClient(Client c);

    public boolean testEch(Echeance ech);
    public void executeEch(Client cl);
    public void upDateEch(Echeance ech);

    public ArrayList<Compte> getCpt(Client c);
    public ArrayList<Categorie> getCatClient(Client c);

    public ArrayList<Operation> getOpsCpt(Compte c);
    public ArrayList<Operation> getOpsClient(Client clt);
    public ArrayList<Operation> getOpsClient(Compte c,char type, Date d1 ,Date d2,boolean testAffectOps);
    public ArrayList<Operation> getOpsClient(Client client,char type, Date d1 ,Date d2,boolean testAffectOps);


    public ArrayList<Echeance> getEchCpt(Compte c);
    public ArrayList<Echeance> getEchClient(Client clt);

    public double getSoldeTotal(Client c);
    public double getAllDepence(Client c);
    public double getAllRevenu(Client c);

    public double getSoldeTotalCpt(Compte c);
    public double getDepenceCpt(Compte c);
    public double getRevenuCpt(Compte c);

    public void saveCat(Categorie c);
    public void saveOps(Operation ops);
    public void saveEch(Echeance ech);
    public void upDateCpt(Compte c);

    public void creatCsvFile(String txt ,String fileDir);
    public void creatPdfFile(String txt ,String fileDir);



}
