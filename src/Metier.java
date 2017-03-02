import java.sql.*;
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
                i.setListeOps(getOpsCpt(i));
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
    public ArrayList<Operation> getOpsCpt(Compte c) {

        ArrayList<Operation> res = new ArrayList<Operation>();
        Operation i ;
        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("SELECT * \n" +
                    "FROM  `operation` \n" +
                    "WHERE  `fk_id_cpt_ops` =  ? ");

            stat.setInt(1,c.getIdCpt());
            ResultSet rq = stat.executeQuery();
            while (rq.next()){
                i=new Operation(0,0,0,"",0,' ',0,0,null);
                i.setIdOps(rq.getInt(1));
                i.setFkCpt(rq.getInt(2));
                i.setFkCat(rq.getInt(3));
                i.setDesTiers(rq.getString(4));
                i.setMontantOps(rq.getDouble(5));
                i.setDateOps(rq.getDate(6));
                i.setTypeOps(rq.getString(7).charAt(0));
                i.setSoldeAvant(rq.getInt(8));
                i.setSoldeApres(rq.getInt(9));
                res.add(i);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return res;
    }

    @Override
    public ArrayList<Operation> getOpsClient(Client clt) {

        ArrayList<Operation> res = new ArrayList<Operation>();

        for (Compte i :clt.getCptClient())
        {
            res.addAll(getOpsCpt(i));
        }

        return res;
    }

    @Override
    public double getSoldeTotal( Client c) {
        double res =0;
        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("SELECT  SUM(solde) \n" +
                    "FROM  `compte` \n" +
                    "WHERE  `fk_id_client_cpt` =  ? ");

            stat.setInt(1,c.getIdClient());
            ResultSet rq = stat.executeQuery();
            System.out.println(rq.getStatement().toString());
            while (rq.next()){
                res=(rq.getDouble(1));
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public double getAllDepence(Client c) {
        double res =0;
        Connection cnx = JDBC.getConnection();
        PreparedStatement  stat;
        try {
            for(Compte i:c.getCptClient()){

                stat = cnx.prepareStatement("SELECT  SUM(montant_ops) \n" +
                        "FROM  `operation` \n" +
                        "WHERE  `fk_id_cpt_ops` =  ? AND `type_ops` =  'd' ");

                stat.setInt(1,i.getIdCpt());
                ResultSet rq = stat.executeQuery();
                System.out.println(rq.getStatement().toString());
                while (rq.next()){
                    res+=(rq.getDouble(1));
                }

            }



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public double getAllRevenu(Client c) {

        double res =0;
        Connection cnx = JDBC.getConnection();
        PreparedStatement  stat;
        try {
            for(Compte i:c.getCptClient()){

                stat = cnx.prepareStatement("SELECT  SUM(montant_ops) \n" +
                        "FROM  `operation` \n" +
                        "WHERE  `fk_id_cpt_ops` =  ? AND `type_ops` =  'r' ");

                stat.setInt(1,i.getIdCpt());
                ResultSet rq = stat.executeQuery();
                System.out.println(rq.getStatement().toString());
                while (rq.next()){
                    res+=(rq.getDouble(1));
                }

            }



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public double getSoldeTotalCpt(Compte c) {
        double res =0;
        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("SELECT  solde \n" +
                    "FROM  `compte` \n" +
                    "WHERE  `id_cpt` =  ? ");

            stat.setInt(1,c.getIdCpt());
            ResultSet rq = stat.executeQuery();
            System.out.println(rq.getStatement().toString());
            while (rq.next()){
                res=(rq.getDouble(1));
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public double getDepenceCpt(Compte c) {
        double res =0;
        Connection cnx = JDBC.getConnection();
        PreparedStatement  stat;
        try {

                stat = cnx.prepareStatement("SELECT  SUM(montant_ops) \n" +
                        "FROM  `operation` \n" +
                        "WHERE  `fk_id_cpt_ops` =  ? AND `type_ops` =  'd' ");

                stat.setInt(1,c.getIdCpt());
                ResultSet rq = stat.executeQuery();
                System.out.println(rq.getStatement().toString());
                while (rq.next()){
                    res+=(rq.getDouble(1));
                }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public double getRevenuCpt(Compte c) {
        double res =0;
        Connection cnx = JDBC.getConnection();
        PreparedStatement  stat;
        try {

            stat = cnx.prepareStatement("SELECT  SUM(montant_ops) \n" +
                    "FROM  `operation` \n" +
                    "WHERE  `fk_id_cpt_ops` =  ? AND `type_ops` =  'r' ");

            stat.setInt(1,c.getIdCpt());
            ResultSet rq = stat.executeQuery();
            System.out.println(rq.getStatement().toString());
            while (rq.next()){
                res+=(rq.getDouble(1));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
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
