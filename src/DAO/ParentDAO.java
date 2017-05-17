package DAO;

import Models.Parent;
import ODB.OracleDBSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
public class ParentDAO implements DAO<Parent>{

    private String            nomTable    = "Parent"    ;
    private String            nomSequence = "SEQ_ID_P" ;
    private String            requete     = ""         ;
    private Connection        session     = null       ;
    private PreparedStatement statement   = null       ;
    private ResultSet         resultat    = null       ;
    private boolean           valide      = false      ;
    private int               seq         =-1          ;

    public ParentDAO(){
      session = OracleDBSingleton.getSession();
    }

    @Override
    public ObservableList<Parent> getAll() {
        return null;
    }

    @Override
    public boolean delAll() {
        return false;
    }

    @Override
    public int create(Parent instance) {
            seq=-1;
        try {
            requete = "INSERT INTO " + nomTable + " (ID_PARENT , NOM_PERE , PROF_P , NOM_MERE , PROF_M , TEL_P , EMAIL_P)  "
                      + "  VALUES ( " + seq_id_next() + " , ? , ? , ? , ? , ? , ?  )";
            statement = session.prepareStatement(requete);
            statement.setString(1, instance.getNOMP());
            statement.setString(2, instance.getPROFP());
            statement.setString(3, instance.getNOMM());
            statement.setString(4, instance.getPROFM());
            statement.setString(5, instance.getTELP());
            statement.setString(6, instance.getEMAILP());
            if (statement.executeUpdate() != 0) {
                seq=seq_id_curr();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return seq;
    }
    public int dernier() {
        int id = -1;
        try {
            requete = "Select ID_PARENT from " + nomTable + " "
                    + " where rowid=(select max(rowid) from " + nomTable + ")";
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                id = resultat.getInt("ID_PARENT");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public Parent find(int id) {
        Parent parent = null;
        try {
            requete = "SELECT * FROM " + nomTable +" WHERE ( ID_PARENT = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                valide = true;
                parent = new Parent();
                parent.setID_PARENT(resultat.getInt("ID_PARENT"));
                parent.setNOMP(resultat.getString("NOM_PERE"));
                parent.setPROFP(resultat.getString("PROF_P"));
                parent.setNOMM(resultat.getString("NOM_MERE"));
                parent.setPROFM(resultat.getString("PROF_M"));
                parent.setEMAILP(resultat.getString("EMAIL_P"));
                parent.setTELP(resultat.getString("TEL_P"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parent;
    }

    @Override
    public boolean update(Parent instance) {
    valide = false;
        try {
            requete = "UPDATE " + nomTable + " SET   "
                    + "NOM_PERE        =  ?  ,"
                    + "NOM_MERE        =  ?  ,"
                    + "PROF_P          =  ?  ,"
                    + "PROF_M          =  ?  ,"
                    + "TEL_P           =  ?  ,"
                    + "EMAIL_P         =  ?  "
                    + "WHERE  ID_PARENT = ? ";
            statement = session.prepareStatement(requete);
            statement.setString(1, instance.getNOMP());
            statement.setString(2, instance.getNOMM());
            statement.setString(3, instance.getPROFP());
            statement.setString(4, instance.getPROFM());
            statement.setString(5, instance.getTELP());
            statement.setString(6, instance.getEMAILP());
            statement.setInt(7, instance.getID_PARENT());

            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valide;

    }

    @Override
    public boolean delete(int id) {
        valide = false;
        try {
            requete = "DELETE FROM " + nomTable + " WHERE ( ID_PARENT = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0){
                valide = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
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

        } catch (SQLException ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
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

        } catch (SQLException ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        System.out.println("sequence curr  "+seq);
        return seq;
    }

}
