package DAO;

import Models.Assiste;
import Models.Emploi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Savepoint;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmploiDAO implements DAO<Emploi> {

    private Connection active_session;
    
    public EmploiDAO() {
        active_session = ODB.OracleDBSingleton.getSession();
    }

    
    
    @Override
    public ObservableList<Emploi> getAll() {
        String requete = "SELECT * FROM EMPLOI" ;
         ObservableList<Emploi> liste = FXCollections.observableArrayList();
         try {
            PreparedStatement statement = active_session.prepareStatement(requete);
            ResultSet resultat = statement.executeQuery();
            while (resultat.next()) {
                Emploi e = new Emploi();
                e.setId(resultat.getInt("ID_EMP"));
                e.setId_assiste(resultat.getInt("REF_AS"));
                e.setId_day(resultat.getInt("JOUR"));
                e.setId_heure(resultat.getInt("HEURE"));
                e.setId_salle(resultat.getInt("REF_S"));
                liste.add(e);
            }
        } catch (Exception exception) {
            System.out.println("Classe : EmploiDAO.java\n"
                    + "Methode : getAll()\n"
                    + "Exception : " + exception);
        }
         return liste;
    }

    @Override
    public boolean delAll() {
        String requete = "DELETE FROM EMPLOI";
        try {
            return (ODB.OracleDBSingleton.getSession().prepareStatement(requete).executeUpdate()>0);
        } catch (SQLException ex) {
            Logger.getLogger(EmploiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean conflict(Emploi instance) {
        ObservableList<Emploi> all = this.getAll();
        for (Emploi i : all) {
            if (i.getId_day() == instance.getId_day()
                    && i.getId_heure() == instance.getId_heure()
                    && i.getId_salle() == instance.getId_salle() 
                    && i.getId_assiste() == instance.getId_assiste()) {
                System.out.println("Conft salle :" + instance);
                return true; 
            }
        }
        return false;
    }

    @Override
    public int create(Emploi instance) {
        String requete = "INSERT INTO EMPLOI (ID_EMP , REF_AS , REF_S , HEURE , JOUR )"
                        + "VALUES  (SEQ_ID_EMP.NEXTVAL ,  ?    ,  ?    ,  ?    ,  ?  )";
        try {
            PreparedStatement st = ODB.OracleDBSingleton.getSession().prepareStatement(requete);
            st.setInt(1, instance.getId_assiste());
            st.setInt(2, instance.getId_salle());
            st.setInt(3, instance.getId_heure());
            st.setInt(4, instance.getId_day());
            if (st.executeUpdate() <= 0) {
                return -1;
            } else {
                requete = "Select ID_EMP from EMPLOI where rowid=(select max(rowid) from EMPLOI ) ";
                ResultSet rs = ODB.OracleDBSingleton.getSession().prepareStatement(requete).executeQuery();
                while (rs.next()) {
                    return rs.getInt("ID_EMP");
                }
                return -1;
            }
        } 
        catch (SQLIntegrityConstraintViolationException ex){
            return -2;
        }
        catch (SQLException ex) {
            Logger.getLogger(EmploiDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1; // Integrite
        }
    }

    @Override
    public Emploi find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Emploi instance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        String requete = "DELETE FROM EMPLOI WHERE ID_EMP = ? ";
        try {
            PreparedStatement st = ODB.OracleDBSingleton.getSession().prepareStatement(requete);
            st.setInt(1, id);
            return (st.executeUpdate()>0);
        } catch (SQLException ex) {
            Logger.getLogger(EmploiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Savepoint getSave() {
        try {
            return  active_session.setSavepoint();
        } catch (SQLException ex) {
            Logger.getLogger(EmploiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void loadSave(Savepoint save){
        try {
            active_session.rollback(save);
        } catch (SQLException ex) {
            Logger.getLogger(EmploiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
