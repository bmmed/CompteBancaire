package Controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

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
        Client res =new Client(0," "," ");
        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("SELECT * \n" +
                    "FROM  `client` \n" +
                    "WHERE  `login_client` =  ? " +
                    "AND  `mp_client` =  ? ");

            stat.setString(1,u);
            stat.setString(2,mp);
            ResultSet rq = stat.executeQuery();
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
    public  void refrechClient(Client c){
        c.setCptClient(getCpt(c));
        c.setCatClient(getCatClient(c));
    }

    @Override
    public boolean testEch(Echeance ech) {
        boolean res=false;

        Calendar calendar = Calendar.getInstance();
        Date dateSys =new Date(calendar.getTime().getTime());


        Date dateLimit ;

            calendar.setTime(ech.getDate_last_ech());
            calendar.add(Calendar.DATE,ech.getPeriode_ech());
            dateLimit =new Date(calendar.getTime().getTime());

            if(dateSys.after(dateLimit))
            {
                res=true;
            }
        return res;
    }

    @Override
    public void executeEch(Client c) {

        Calendar calendar = Calendar.getInstance();
        Date dateSys =new Date(calendar.getTime().getTime());

        for(Compte cpt:c.getCptClient())
        {
            for (Echeance ech:cpt.getListeEch())
            {
                if(this.testEch(ech))
                {
                    this.saveOps(cpt.creatDepence(ech.getFk_id_cat_ech(),ech.getDes_ech()+" de la date  "+dateSys,ech.getMontant_ech()));
                    this.upDateCpt(cpt);
                    ech.setDate_last_ech(dateSys);
                    this.upDateEch(ech);
                }
            }
        }
        this.refrechClient(c);
    }

    @Override
    public void upDateEch(Echeance ech) {
        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("UPDATE `echeance` SET `fk_id_cpt_ech`=?," +
                    "`fk_id_cat_ech`= ? ,`montant_ech`= ? ," +
                    "`periode_ech`= ? ,`dess_ech`= ? ,`date_last_ech`= ? WHERE id_ech = ?");

            stat.setInt(1, ech.getFk_id_cpt_ech());
            stat.setInt(2, ech.getFk_id_cat_ech());
            stat.setDouble(3, ech.getMontant_ech());
            stat.setInt(4, ech.getPeriode_ech());
            stat.setString(5, ech.getDes_ech());
            stat.setDate(6, ech.getDate_last_ech());

            stat.setInt(7, ech.getIdEch());

            stat.executeUpdate();
            stat.close();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
                i.setListeEch(getEchCpt(i));
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
    public ArrayList<Echeance> getEchCpt(Compte c) {

        ArrayList<Echeance> res = new ArrayList<Echeance>();
        Echeance i ;
        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("SELECT * \n" +
                    "FROM  `echeance` \n" +
                    "WHERE  `fk_id_cpt_ech` =  ? ");

            stat.setInt(1,c.getIdCpt());
            ResultSet rq = stat.executeQuery();
            while (rq.next()){
                i=new Echeance();
                i.setIdEch(rq.getInt(1));
                i.setFk_id_cpt_ech(rq.getInt(2));
                i.setFk_id_cat_ech(rq.getInt(3));
                i.setMontant_ech(rq.getDouble(4));
                i.setPeriode_ech(rq.getInt(5));
                i.setDes_ech(rq.getString(6));
                i.setDate_last_ech(rq.getDate(7));
                res.add(i);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public ArrayList<Echeance> getEchClient(Client clt) {

        ArrayList<Echeance> res = new ArrayList<Echeance>();

        for (Compte i :clt.getCptClient())
        {
            res.addAll(getEchCpt(i));
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
                    i.setSoldeAvant(rq.getDouble(8));
                    i.setSoldeApres(rq.getDouble(9));
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
    public ArrayList<Operation> getOpsClient(Compte c, char type, Date d1, Date d2, boolean testAffectOps) {


        ArrayList<Operation> res = new ArrayList<Operation>();
        Operation i ;
        Connection cnx = JDBC.getConnection();

        String sql="SELECT * \n" + "FROM  `operation` WHERE  `fk_id_cpt_ops` =  ? ";

        if(type!=' ') sql+=" AND `type_ops` = \'"+type+"\'";
        if(d1!=null || d2 != d2) sql+= " AND  (`date_ops` BETWEEN \'"+d1+"\' AND \'"+d2+"\' )";
        if(testAffectOps) sql+= " AND `fk_id_cat_ops` = 0 ";

        System.out.println(sql);

        try {
            PreparedStatement  stat = cnx.prepareStatement(sql);

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
                i.setSoldeAvant(rq.getDouble(8));
                i.setSoldeApres(rq.getDouble(9));
                res.add(i);

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return res;
    }

    @Override
    public ArrayList<Operation> getOpsClient(Client client, char type, Date d1, Date d2, boolean testAffectOps) {
       ArrayList<Operation> res = new ArrayList<Operation>();

       for (Compte i:client.getCptClient())
       {
           res.addAll(this.getOpsClient(i,type,d1,d2,testAffectOps));
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
    public void saveCat(Categorie c) {

        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("INSERT INTO `categorie_ops`(`des_cat`, `fk_id_client_cat`) " +
                    "VALUES (?,?)");

            stat.setString(1, c.getDesCat());
            stat.setInt(2, c.getFkClient());

            stat.executeUpdate();
            stat.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
    public void saveEch(Echeance ech) {
        Connection cnx = JDBC.getConnection();
        try {
            PreparedStatement  stat = cnx.prepareStatement("INSERT INTO `echeance`" +
                            "(`fk_id_cpt_ech`, `fk_id_cat_ech`, `montant_ech`, `periode_ech`, `dess_ech`, `date_last_ech`) " +
                    "VALUES (?,?,?,?,?,?)");

            stat.setInt(1, ech.getFk_id_cpt_ech());
            stat.setInt(2, ech.getFk_id_cat_ech());
            stat.setDouble(3, ech.getMontant_ech());
            stat.setInt(4, ech.getPeriode_ech());
            stat.setString(5, ech.getDes_ech());
            stat.setDate(6, ech.getDate_last_ech());

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
    @Override
    public void creatCsvFile(String txt, String fileDir) {

        try {
            FileWriter writer = new FileWriter(fileDir);
            writer.append(txt);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creatPdfFile(String txt, String fileDir) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileDir));
            document.open();
            document.add(new Paragraph(txt));
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
