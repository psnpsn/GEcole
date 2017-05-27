
package DAO;
import Models.Absence;
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

public class AbsenceDAO implements DAO<Absence>  {
     private String            nomTable    = "ABSENCE"    ;
    private String            nomSequence = "SEQ_ID_ABS" ;
    private String            requete     = ""         ;
    private Connection        session     = null       ;
    private PreparedStatement statement   = null       ;
    private ResultSet         resultat    = null       ;
    private boolean           valide      = false      ;
    private int               seq         =-1          ;

    public AbsenceDAO(){
              session = OracleDBSingleton.getSession();
    }

@Override
    public ObservableList<Absence> getAll() {
        ArrayList<Absence> liste = new ArrayList<Absence>();
        try {
            requete = "SELECT * FROM " + nomTable;
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                Absence absence = new Absence();
                absence.setId_absence(resultat.getInt("ID_ABSENCE"));
                absence.setDate_abs(resultat.getTimestamp("DATE_ABS"));
                absence.setRef_e(resultat.getInt("REF_E"));
                absence.setRef_sc(resultat.getInt("REF_SC"));
                liste.add(absence);
            }
        } catch (SQLException ex) { 
             Logger.getLogger(AbsenceDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        ObservableList<Absence> list = FXCollections.observableArrayList(liste);
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
             Logger.getLogger(AbsenceDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return valide;
    }


    public int create(Absence instance) {
    valide = false;
        try {
            requete = "INSERT INTO " + nomTable + " (ID_ABSENCE , DATE_ABS , REF_SC, REF_E  )  "
                      + "  VALUES ( " + seq_id_next() + " , ? , ? , ? )";
            statement = session.prepareStatement(requete);            
            statement.setDate(1,new java.sql.Date(instance.getDate_abs().getTime()));                        
            statement.setInt(2, instance.getRef_sc());
            statement.setInt(3, instance.getRef_e());
            
            if (statement.executeUpdate() != 0) {
                seq=seq_id_curr();
            }
        } catch (SQLException ex) { 
             Logger.getLogger(AbsenceDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return seq;
    }
  @Override
    public Absence find(int id) {
      Absence absence = null;
        try {
            requete = "SELECT * FROM INST WHERE  ID_ABSENCE = ? ";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                valide = true;
                absence = new Absence();
                absence.setId_absence(resultat.getInt("ID_ABSENCE"));
                absence.setDate_abs(resultat.getTimestamp("DATE_ABS"));
                absence.setRef_e(resultat.getInt("REF_E"));                
                absence.setRef_sc(resultat.getInt("REF_SC"));                
                return absence;
            }
        } catch (SQLException ex) { 
             Logger.getLogger(AbsenceDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return absence;
    }

    public boolean update(Absence instance) {
    valide = false;
        try {
            requete = "UPDATE " + nomTable + " SET   "
                    + "DATE_ABS      =  ?  ,"
                    + "REF_SC        =  ?  ,"
                    + "REF_E           =  ?  "
                    + "WHERE  ID_ABSENCE     =  ? ";
            statement = session.prepareStatement(requete);

            statement.setDate(1,new java.sql.Date(instance.getDate_abs().getTime()));
            statement.setInt(2, instance.getRef_sc());
            statement.setInt(3, instance.getRef_e());
            statement.setInt(4, instance.getId_absence());

            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (SQLException ex) {
             Logger.getLogger(AbsenceDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return valide;
    }

    @Override
    public boolean delete(int id) {
        valide = false;
        try {
            requete = "DELETE FROM " + nomTable + " WHERE ( ID_ABSENCE = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0){
                valide = true;
            }
        } catch (SQLException ex) { 
             Logger.getLogger(AbsenceDAO.class.getName()).log(Level.SEVERE, null, ex);
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
