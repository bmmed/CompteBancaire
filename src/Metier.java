import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by BMMed on 01/03/2017.
 */
public class Metier implements IMetier {
    @Override
    public boolean testClient(String u, String mp) {
        boolean res =false;
        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("SELECT * \n" +
                    "FROM  `client` \n" +
                    "WHERE  `login_client` =  ? " +
                    "AND  `mp_client` =  ? ");

            stat.setString(1,u);
            stat.setString(2,mp);
            ResultSet rq = stat.executeQuery();
            if(rq.next()) res=true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return res;
    }

    @Override
    public Client cnxClient(String u, String mp) {
        Client res =new Client(0,"","");
        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("SELECT * \n" +
                    "FROM  `client` \n" +
                    "WHERE  `login_client` =  ? " +
                    "AND  `mp_client` =  ? ");

            stat.setString(1,u);
            stat.setString(2,mp);
            ResultSet rq = stat.executeQuery();
            System.out.println(rq.getStatement().toString());
            while (rq.next()){
                res.setIdClient(rq.getInt(1));
                res.setNomClient(rq.getString(2));
                res.setPrenomClient(rq.getString(3));
            }

            res.setCptClient(getCpt(res));
            res.setCatClient(getCatClient(res));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return res;
    }

    @Override
    public ArrayList<Compte> getCpt(Client c) {

        ArrayList<Compte> res = new ArrayList<Compte>();
        Compte i ;
        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("SELECT * \n" +
                    "FROM  `compte` \n" +
                    "WHERE  `fk_id_client_cpt` =  ? ");

            stat.setInt(1,c.getIdClient());
            ResultSet rq = stat.executeQuery();
            while (rq.next()){
                i=new Compte(0,0,0, "");
                i.setIdCpt(rq.getInt(1));
                i.setFkClient(rq.getInt(2));
                i.setSolde(rq.getInt(3));
                i.setDesCpt(rq.getString(4));
                res.add(i);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return res;
    }

    @Override
    public ArrayList<Categorie> getCatClient(Client c) {

        ArrayList<Categorie> res = new ArrayList<Categorie>();
        Categorie i ;
        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("SELECT * \n" +
                    "FROM  `categorie_ops` \n" +
                    "WHERE  `fk_id_client_cat` =  ? OR `fk_id_client_cat` =  0 ");

            stat.setInt(1,c.getIdClient());
            ResultSet rq = stat.executeQuery();
            while (rq.next()){
                i=new Categorie(0,"",0);
                i.setIdCat(rq.getInt(1));
                i.setDesCat(rq.getString(2));
                i.setFkClient(rq.getInt(3));
                res.add(i);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return res;
    }

    @Override
    public ArrayList<Operation> getOps() {
        return null;
    }

    @Override
    public double getSoldeTotal() {
        return 0;
    }

    @Override
    public double getDepence() {
        return 0;
    }

    @Override
    public double getRevenu() {
        return 0;
    }

    @Override
    public double getSoldeTotal(Compte c) {
        return 0;
    }

    @Override
    public double getDepence(Compte c) {
        return 0;
    }

    @Override
    public double getRevenu(Compte c) {
        return 0;
    }

    @Override
    public void addCat(Categorie c) {

    }

    @Override
    public void saveOps(Operation ops) {
        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("INSERT INTO `operation`(`fk_id_cpt_ops`, `fk_id_cat_ops`, `des_tiers_ops`," +
                    " `montant_ops`, `date_ops`, `type_ops` ,`solde_avant`, `solde_apres`) " +
                    "VALUES (?,?,?,?,?,?,?,?)");

            stat.setInt(1, ops.getFkCpt());
            stat.setInt(2, ops.getFkCat());
            stat.setString(3, ops.getDesTiers());
            stat.setDouble(4, ops.getMontantOps());
            stat.setDate(5, ops.getDateOps());
            stat.setString(6, String.valueOf(ops.getTypeOps()));
            stat.setDouble(7, ops.getSoldeAvant());
            stat.setDouble(8, ops.getSoldeApres());



            stat.executeUpdate();
            stat.close();



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void upDateCpt(Compte c) {
        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("UPDATE `compte` SET `solde`= ? ,`des_cpt`= ? WHERE id_cpt = ?");

            stat.setDouble(1, c.getSolde());
            stat.setString(2, c.getDesCpt());
            stat.setInt(3, c.getIdCpt());

            stat.executeUpdate();
            stat.close();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
