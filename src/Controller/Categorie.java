package Controller;

/**
 * Created by BMMed on 28/02/2017.
 */
public class Categorie {
    private int idCat;
    private String desCat;
    private int fkClient;

    public Categorie(int idCat, String desCat, int fkClient) {
        this.idCat = idCat;
        this.desCat = desCat;
        this.fkClient = fkClient;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public String getDesCat() {
        return desCat;
    }

    public void setDesCat(String desCat) {
        this.desCat = desCat;
    }

    public int getFkClient() {
        return fkClient;
    }

    public void setFkClient(int fkClient) {
        this.fkClient = fkClient;
    }


}

