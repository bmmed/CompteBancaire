import java.util.ArrayList;

/**
 * Created by BMMed on 28/02/2017.
 */
public interface IMetier {
    public boolean testClient(String u ,String mp);
    public Client cnxClient(String u ,String mp);
    public ArrayList<Compte> getCpt(Client c);
    public ArrayList<Categorie> getCatClient(Client c);
    public  void addCat(Categorie c);
    public double getSoldeTotal();
    public double getDepence();
    public double getRevenu();
    public double getSoldeTotal(Compte c);
    public double getDepence(Compte c);
    public double getRevenu(Compte c);

    public ArrayList<Operation> getOps();

}
