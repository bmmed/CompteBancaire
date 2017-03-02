import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by BMMed on 28/02/2017.
 */
public class Compte {
    private int idCpt;
    private int fkClient;
    private double solde;
    private String desCpt;
    private ArrayList<Operation> listeOps;

    public ArrayList<Operation> getListeOps() {
        return listeOps;
    }

    public void setListeOps(ArrayList<Operation> listeOps) {
        this.listeOps = listeOps;
    }

    public Compte(int idCpt, int fkClient, double solde, String desCpt) {
        this.idCpt = idCpt;
        this.fkClient = fkClient;
        this.solde = solde;
        this.desCpt = desCpt;
    }

    public int getIdCpt() {
        return idCpt;
    }

    public void setIdCpt(int idCpt) {
        this.idCpt = idCpt;
    }

    public int getFkClient() {
        return fkClient;
    }

    public void setFkClient(int fkClient) {
        this.fkClient = fkClient;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getDesCpt() {
        return desCpt;
    }

    public void setDesCpt(String desCpt) {
        this.desCpt = desCpt;
    }

    public  synchronized Operation creatRevenu(int idCat, String desTiers, double montant , Date dateops){

        Operation res=new Operation(0,this.idCpt, idCat,desTiers,montant,'r',
                    this.getSolde(),this.getSolde()+montant,dateops);

        this.solde+=montant;

        return res;

    }

    public Operation creatDepence(int idCat, String desTiers, double montant , Date dateops){

        Operation res=new Operation(0,this.idCpt, idCat,desTiers,montant,'d',
                this.getSolde(),this.getSolde()-montant,dateops);
        this.solde-=montant;

        return res;

    }
}
