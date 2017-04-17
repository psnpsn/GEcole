package DAO;

import Models.Emploi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

public class EmploiDAO implements DAO<Emploi> {

    @Override
    public ObservableList<Emploi> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public int create(Emploi instance) {
        String requete = "INSERT INTO EMPLOI ID_EMP            , REF_AS , REF_S , HEURE , JOUR "
                        + "VALUES "
                                          + "( SEQ_ID_EMP.NEXTVAL ,  ?    ,  ?    ,  ?    ,  ? ) ";
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
        } catch (SQLException ex) {
            Logger.getLogger(EmploiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
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



}
