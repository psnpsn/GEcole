package DAO;

import Models.Classe;
import ODB.OracleDBSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

public class AppartientDAO {

    private Connection session = null;

    public AppartientDAO() {
        session = OracleDBSingleton.getSession();
    }

    // assigne un ou plusieur eleve a une classe
    public int assigner(int id_classe, int ... id_eleves) {
        int s = 0;
        EleveDAO daoe = new EleveDAO();
        for (int i = 0; i < id_eleves.length; i++) {
            String requete = "INSERT INTO APPARTIENT (REF_C  , REF_E ) VALUES ( ? , ? )";
            PreparedStatement ps;
            try {
                ps = session.prepareStatement(requete);
                ps.setInt(1,id_classe);
                ps.setInt(2, id_eleves[i]);
                s += ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(AppartientDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return s;
    }
    
    public boolean  appartient(int id_c , int id_e){
        boolean appartient = false;
        String requete = "SELECT * FROM APPARTIENT WHERE REF_C = ? AND REF_E = ? " ;
        PreparedStatement ps;
            try {
                ps = session.prepareStatement(requete);
                ps.setInt(1,id_c);
                ps.setInt(2, id_e);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    appartient = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(AppartientDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return appartient;
    }
    public boolean  appartient(int id_e){
        boolean appartient = false;
        String requete = "SELECT * FROM APPARTIENT WHERE REF_E = ? " ;
        PreparedStatement ps;
            try {
                ps = session.prepareStatement(requete);
                ps.setInt(1, id_e);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    appartient = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(AppartientDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return appartient;
    }
    
    
    // a remplacer par un trigger oracle
    public void mettre_a_jour_nb_eleves(){
        ClasseDAO cdao = new ClasseDAO();
        ObservableList<Classe> class_list = cdao.getAll();
        for (Classe c : class_list){
            c.setNbE(0);
            String requete = "SELECT COUNT(*) NBR FROM APPARTIENT WHERE REF_C = ? " ;
            try {
                PreparedStatement ps = session.prepareStatement(requete);
                ps.setInt(1, c.getId_c());
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    c.setNbE(rs.getInt("NBR"));
                    System.out.println("Classe =" + c.getNom()+ " - nombre d'eleve ="+c.getNbE());
                    cdao.update(c);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(AppartientDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}