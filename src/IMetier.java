import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by BMMed on 28/02/2017.
 */
public interface IMetier {
    public boolean testClient(String u ,String mp);
    public Client cnxClient(String u ,String mp);

    public ArrayList<Compte> getCpt(Client c);
    public ArrayList<Categorie> getCatClient(Client c);

    public ArrayList<Operation> getOpsCpt(Compte c);
    public ArrayList<Operation> getOpsClient(Client clt);

    public double getSoldeTotal(Client c);
    public double getAllDepence(Client c);
    public double getAllRevenu(Client c);

    public double getSoldeTotalCpt(Compte c);
    public double getDepenceCpt(Compte c);
    public double getRevenuCpt(Compte c);

    public  void addCat(Categorie c);
    public void saveOps(Operation ops);
    public void upDateCpt(Compte c);




}
