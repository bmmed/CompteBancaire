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

    private ArrayList<Operation> listeOps=null;
    private ArrayList<Echeance> listeEch=null;

    public ArrayList<Echeance> getListeEch() {
        return listeEch;
    }

    public void setListeEch(ArrayList<Echeance> listeEch) {
        this.listeEch = listeEch;
    }

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

        Operation res= null;

            res = new Operation(0,this.idCpt, idCat,desTiers,montant,'r',
                        this.getSolde(),this.getSolde()+montant,dateops);


        this.solde+=montant;

        return res;

    }

    public Operation creatDepence(int idCat, String desTiers, double montant , Date dateops){


        Operation res=null;

            res=new Operation(0,this.idCpt, idCat,desTiers,montant,'d',
                    this.getSolde(),this.getSolde()-montant,dateops);
            this.solde-=montant;


        return res;

    }

    public Echeance creatEch( int fk_cat, double montant , int periode, String desEch, Date lastEch){

        Echeance res=new Echeance();
        res.setFk_id_cpt_ech(this.getIdCpt());
        res.setDes_ech(desEch);
        res.setFk_id_cat_ech(fk_cat);
        res.setMontant_ech(montant);
        res.setPeriode_ech(periode);
        res.setDate_last_ech(lastEch);

        return res;

    }

    public String getCvsCpt(){
        String res="\n";
        res+="  Identifiant du compte: "+this.getIdCpt()+"  Désignation: "+this.getDesCpt()+" solde: "+this.getSolde()+" EUROS\n";

        res+="\n    -Les echeances du compte: \n";
        for(Echeance i:this.getListeEch())
        {
            res+="        "+i.getIdEch()+" , "+i.getDes_ech()+" , "+i.getMontant_ech()+" , "+i.getPeriode_ech()+" , "+i.getDate_last_ech()+"\n";
        }

        res+="\n    -Les operations du compte: \n";
        for(Operation i:this.getListeOps())
        {
            res+="        "+i.getIdOps()+" , "+i.getDateOps()+" , "+i.getDesTiers()+" , "+i.getMontantOps()+" , "+i.getSoldeAvant()+" , "
                    +i.getSoldeApres()+" , "+i.getFkCat()+"\n";
        }
        return res;
    }

}
