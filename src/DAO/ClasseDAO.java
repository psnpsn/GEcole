
package DAO;

import Models.Classe;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClasseDAO implements DAO<Classe>{
    
    @Override
    public ObservableList<Classe> getAll() {
        ObservableList<Classe> liste = FXCollections.observableArrayList();
        String requete  = "SELECT * FROM CLASSE";
        try {
            PreparedStatement ps = ODB.OracleDBSingleton.getSession().prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                 Classe c = new Classe();
                 c.setId_c(rs.getInt("ID_CLASSE"));
                 c.setNom(rs.getString("NOM"));
                 c.setCapacite(rs.getInt("CAPACITE"));
                 c.setNbE(rs.getInt("NB_ELEVES"));
                 c.setRef_niv(rs.getInt("REF_NIV"));
                 liste.add(c);
             }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    @Override
    public boolean delAll() {
        boolean valide = false;
        try {
            PreparedStatement statement = ODB.OracleDBSingleton.getSession().prepareStatement("DELETE FROM CLASSE");
           if ( statement.executeUpdate()!=0)
                valide = true;
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valide;
    }

    @Override
    public int create(Classe instance) {
        int id = -1;
        String requete = "INSERT INTO CLASSE ( ID_CLASSE , NOM , CAPACITE , REF_NIV ) VALUES ( SEQ_ID_C.NEXTVAL , ? , ? , ? )" ;
        try {
            PreparedStatement p2,ps = ODB.OracleDBSingleton.getSession().prepareStatement(requete);
            ps.setString(1,instance.getNom());
            ps.setInt(2, instance.getCapacite());
            ps.setInt(3, instance.getRef_niv());
            if (ps.executeUpdate() > 0) {
                p2 = ODB.OracleDBSingleton.getSession().prepareStatement("Select ID_CLASSE from CLASSE where rowid=(select max(rowid) from CLASSE) ");
                ResultSet resultat = p2.executeQuery();
                while (resultat.next()) {
                    return resultat.getInt("ID_CLASSE");
                }
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
     return id;   
    }

    @Override
    public Classe find(int id) {
        Classe c = null;
        try {
            String requete = "SELECT * FROM CLASSE WHERE  ID_CLASSE = ? ";
            PreparedStatement ps = ODB.OracleDBSingleton.getSession().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new Classe();
                c.setId_c(id);
                c.setNom(rs.getString("NOM"));
                c.setCapacite(rs.getInt("CAPACITE"));
                c.setNbE(rs.getInt("NB_ELEVES"));
                c.setRef_niv(rs.getInt("REF_NIV"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public boolean update(Classe instance) {
        Boolean valide = false;
        try {
            String requete = "UPDATE CLASSE SET   "
                    + "NOM           =  ?  ,"
                    + "CAPACITE        =  ?  ,"
                    + "REF_NIV =  ?  ,"
                    + "NB_ELEVES = ? "
                    + "WHERE  ID_CLASSE = ? ";
            PreparedStatement statement = ODB.OracleDBSingleton.getSession().prepareStatement(requete);
            statement.setString(1, instance.getNom());
            statement.setInt(2, instance.getCapacite());
            statement.setInt(3, instance.getRef_niv());
            statement.setInt(4, instance.getNbE());
            statement.setInt(5, instance.getId_c());
            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valide;
    }

    @Override
    public boolean delete(int id) {
        boolean valide = false;
        try {
            String requete = "DELETE FROM CLASSE WHERE ( ID_CLASSE = ? )";
            PreparedStatement statement = ODB.OracleDBSingleton.getSession().prepareStatement(requete);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                valide = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valide;
    }
    
}
