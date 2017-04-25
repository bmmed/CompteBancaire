package code;

import java.sql.Date;

/**
 * Created by BMMed on 28/02/2017.
 */
public class Operation {
    private int idOps;
    private int fkCpt;
    private int fkCat;
    private String desTiers;
    private double montantOps;
    private char typeOps;
    private double soldeAvant;
    private double soldeApres;
    private Date dateOps;

    public Operation(int idOps, int fkCpt, int fkCat, String desTiers, double montantOps, char typeOps, double soldeAvant, double soldeApres, Date dateOps) {

            this.idOps = idOps;
            this.fkCpt = fkCpt;
            this.fkCat = fkCat;
            this.desTiers = desTiers;
            this.montantOps = montantOps;
            this.typeOps = typeOps;
            this.soldeAvant = soldeAvant;
            this.soldeApres = soldeApres;
            this.dateOps = dateOps;
    }

    public double getSoldeAvant() {
        return soldeAvant;
    }

    public void setSoldeAvant(double soldeAvant) {
        this.soldeAvant = soldeAvant;
    }

    public double getSoldeApres() {
        return soldeApres;
    }

    public void setSoldeApres(double soldeApres) {
        this.soldeApres = soldeApres;
    }

    public int getIdOps() {
        return idOps;
    }

    public void setIdOps(int idOps) {
        this.idOps = idOps;
    }

    public int getFkCpt() {
        return fkCpt;
    }

    public void setFkCpt(int fkCpt) {
        this.fkCpt = fkCpt;
    }

    public int getFkCat() {
        return fkCat;
    }

    public void setFkCat(int fkCat) {
        this.fkCat = fkCat;
    }

    public String getDesTiers() {
        return desTiers;
    }

    public void setDesTiers(String desTiers) {
        this.desTiers = desTiers;
    }

    public double getMontantOps() {
        return montantOps;
    }

    public void setMontantOps(double montantOps) {
        this.montantOps = montantOps;
    }

    public Date getDateOps() {
        return dateOps;
    }

    public void setDateOps(Date dateOps) {
        this.dateOps = dateOps;
    }

    public char getTypeOps() {
        return typeOps;
    }

    public void setTypeOps(char typeOps) {
        this.typeOps = typeOps;
    }
}
