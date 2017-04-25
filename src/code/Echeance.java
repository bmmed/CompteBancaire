package code;

import java.util.Date;

/**
 * Created by BMMed on 02/03/2017.
 */
public class Echeance {
    private int idEch;
    private int fk_id_cpt_ech;
    private int fk_id_cat_ech;
    private double montant_ech;
    private int periode_ech;
    private String des_ech;
    private Date date_last_ech;



    public int getIdEch() {
        return idEch;
    }

    public void setIdEch(int idEch) {
        this.idEch = idEch;
    }

    public int getFk_id_cpt_ech() {
        return fk_id_cpt_ech;
    }

    public void setFk_id_cpt_ech(int fk_id_cpt_ech) {
        this.fk_id_cpt_ech = fk_id_cpt_ech;
    }

    public int getFk_id_cat_ech() {
        return fk_id_cat_ech;
    }

    public void setFk_id_cat_ech(int fk_id_cat_ech) {
        this.fk_id_cat_ech = fk_id_cat_ech;
    }

    public double getMontant_ech() {
        return montant_ech;
    }

    public void setMontant_ech(double montant_ech) {
        this.montant_ech = montant_ech;
    }

    public int getPeriode_ech() {
        return periode_ech;
    }

    public void setPeriode_ech(int periode_ech) {
        this.periode_ech = periode_ech;
    }

    public String getDes_ech() {
        return des_ech;
    }

    public void setDes_ech(String des_ech) {
        this.des_ech = des_ech;
    }

    public Date getDate_last_ech() {
        return date_last_ech;
    }

    public void setDate_last_ech(Date date_last_ech) {
        this.date_last_ech = date_last_ech;
    }
}
