
package DAO;
import Models.Absence;
import Models.Appreciation;
import Models.Instituteur;
import ODB.OracleDBSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppreciationDAO implements DAO<Appreciation>  {
     private String            nomTable    = "APPRECIATION"    ;
    private String            nomSequence = "SEQ_ID_APPR" ;
    private String            requete     = ""         ;
    private Connection        session     = null       ;
    private PreparedStatement statement   = null       ;
    private ResultSet         resultat    = null       ;
    private boolean           valide      = false      ;
    private int               seq         =-1          ;

    public AppreciationDAO(){
              session = OracleDBSingleton.getSession();
    }

@Override
    public ObservableList<Appreciation> getAll() {
        ArrayList<Appreciation> liste = new ArrayList<Appreciation>();
        try {
            requete = "SELECT * FROM " + nomTable;
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                Appreciation a = new Appreciation();
                a.setId_appr(resultat.getInt("ID_APPR"));
                a.setContenu(resultat.getString("CONTENU"));
                a.setRef_e(resultat.getInt("REF_E"));
                a.setRef_inst(resultat.getInt("REF_INST"));
                a.setRef_module(resultat.getInt("REF_MODULE"));
                liste.add(a);
            }
        } catch (SQLException ex) { 
             Logger.getLogger(AppreciationDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        ObservableList<Appreciation> list = FXCollections.observableArrayList(liste);
        return list;
    }
       @Override
    public boolean delAll() {
        valide = false;
        try {
            requete = "DELETE FROM " + nomTable;
            statement = session.prepareStatement(requete);
           if ( statement.executeUpdate()!=0)
                valide = true;
        } catch (SQLException ex) { 
             Logger.getLogger(AppreciationDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return valide;
    }


     @Override
    public int create(Appreciation instance) {
    valide = false;
        try {
            requete = "INSERT INTO " + nomTable + " (ID_APPR , CONTENU , REF_E, REF_INST , REF_MODULE  )  "
                      + "  VALUES ( " + seq_id_next() + " , ? , ? , ? , ?)";
            statement = session.prepareStatement(requete);            
            statement.setString(1 , instance.getContenu());                        
            statement.setInt(2, instance.getRef_e());
            statement.setInt(3, instance.getRef_inst());
            statement.setInt(4, instance.getRef_module());
            
            if (statement.executeUpdate() != 0) {
                seq=seq_id_curr();
            }
        } catch (SQLException ex) { 
             Logger.getLogger(AppreciationDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return seq;
    }
  @Override
    public Appreciation find(int id) {
      Appreciation a = null;
        try {
            requete = "SELECT * FROM INST WHERE  REF_INST = ? ";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                valide = true;
                a = new Appreciation();
                a.setId_appr(resultat.getInt("ID_APPR"));
                a.setContenu(resultat.getString("CONTENU"));
                a.setRef_e(resultat.getInt("REF_E"));                
                a.setRef_inst(resultat.getInt("REF_INST"));
                a.setRef_module(resultat.getInt("REF_MODULE"));
                return a;
            }
        } catch (SQLException ex) { 
             Logger.getLogger(AppreciationDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return a;
    }

     @Override
    public boolean update(Appreciation instance) {
    valide = false;
        try {
            requete = "UPDATE " + nomTable + " SET   "
                    + "CONTENU        =  ?  ,"
                    + "REF_E          =  ?  ,"
                    + "REF_MODULE     =  ?  ,"
                    + "REF_INST       =  ?  "
                    + "WHERE  ID_APPR =  ? ";
            statement = session.prepareStatement(requete);

            statement.setString(1,instance.getContenu());
            statement.setInt(2, instance.getRef_e());
            statement.setInt(3, instance.getRef_module());
            statement.setInt(4, instance.getRef_inst());
            statement.setInt(4, instance.getId_appr());

            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (SQLException ex) {
             Logger.getLogger(AppreciationDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return valide;
    }

    @Override
    public boolean delete(int id) {
        valide = false;
        try {
            requete = "DELETE FROM " + nomTable + " WHERE ( ID_APPR = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0){
                valide = true;
            }
        } catch (SQLException ex) { 
             Logger.getLogger(AppreciationDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return valide;
    }

    private int seq_id_next(){
        try {
            requete = "SELECT " +nomSequence+ ".nextval FROM DUAL";
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                seq=resultat.getInt("NEXTVAL");
            }

        } catch (Exception exception) {
            System.out.println("Classe : EleveDAO.java\n"
                    + "Methode : seq_id_next\n"
                    + "Exception : " + exception);
        }
        System.out.println("sequence nextval "+seq);
        return seq;
    }

    public int seq_id_curr(){
    try {
            requete = "SELECT " +nomSequence+ ".currval FROM DUAL";
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                seq=resultat.getInt("CURRVAL");
            }

        } catch (Exception exception) {
            System.out.println("Classe : EleveDAO.java\n"
                    + "Methode : seq_id_curr\n"
                    + "Exception : " + exception);
        }

        System.out.println("sequence curr  "+seq);
        return seq;
    }

}
