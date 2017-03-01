/**
 * Created by BMMed on 28/02/2017.
 */
public class Compte {
    private int idCpt;
    private int fkClient;
    private double solde;

    public Compte(int idCpt, int fkClient, double solde) {
        this.idCpt = idCpt;
        this.fkClient = fkClient;
        this.solde = solde;
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
}
