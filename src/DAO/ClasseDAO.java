
package DAO;

import Models.Classe;
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

/**
 *
 * @author DELL
 */
public class ClasseDAO implements DAO<Classe> {
    
     private String            nomTable    = "CLASSE"    ;
    private String            nomSequence = "SEQ_ID_C" ;
    private String            requete     = ""         ;
    private String            requete2     = ""         ;
    private Connection        session     = null       ;
    private PreparedStatement statement   = null       ;
    private PreparedStatement statement2   = null       ;
    private ResultSet         resultat    = null       ;
    private ResultSet         resultat2    = null       ;
    private boolean           valide      = false      ;
    private int               seq         =-1          ;
    
    
    
    public ClasseDAO(){
      session = OracleDBSingleton.getSession();
    }

    @Override
    public ObservableList<Classe> getAll() {
        ObservableList<Classe> liste = FXCollections.observableArrayList();
        try {
            requete = "SELECT * FROM " + nomTable ;
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                Classe classe = new Classe();
                classe.setId_c(resultat.getInt("ID_CLASSE"));
                classe.setNom(resultat.getString("NOM"));
                classe.setCapacite(resultat.getInt("CAPACITE"));
                classe.setNbE(resultat.getInt("NB_ELEVES"));
                classe.setRef_niv(resultat.getInt("REF_NIV"));
                liste.add(classe);
                System.out.println(classe.getNbE());

            }
        } catch (Exception exception) {
            System.out.println("Classe : ClasseDAO.java\n"
                    + "Methode : getAll()\n"
                    + "Exception : " + exception);
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
        seq =-1 ;
        try {
            requete = "INSERT INTO " + nomTable + " (ID_CLASSE , NOM , CAPACITE , REF_NIV , NB_ELEVES )  "
                      + "  VALUES ( " + seq_id_next() + " , ? , ? , ? , 0 )";
            statement = session.prepareStatement(requete);
            statement.setString(1, instance.getNom());
            statement.setInt(2, instance.getCapacite());
            statement.setInt(3, instance.getRef_niv());
            
            if (statement.executeUpdate() != 0) {
                seq=seq_id_curr();
            }
        } catch (Exception exception) {
            System.out.println("Classe : Classe.java\n"
                    + "Methode : create(Classe instance)\n"
                    + "Exception : " + exception);
        }
        
        return seq;
    }

    @Override
    public Classe find(int id) {
        Classe classe= null;
        try {
            requete = "SELECT * FROM " + nomTable +" WHERE ( ID_CLASSE = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                valide = true;
                classe = new Classe();
                classe.setId_c(resultat.getInt("ID_CLASSE"));
                classe.setNom(resultat.getString("NOM"));
                classe.setCapacite(resultat.getInt("CAPACITE"));
                classe.setRef_niv(resultat.getInt("REF_NIV"));
                classe.setNbE(resultat.getInt("NB_ELEVES"));
            }

        } catch (Exception exception) {
            System.out.println("Classe : ClasseDAO.java\n"
                    + "Methode : findByID()\n"
                    + "Exception : " + exception);
        }
        return classe;
    }

    @Override
    public boolean update(Classe instance) {
        valide = false;
        try {
            requete = "UPDATE " + nomTable + " SET   "
                    + "NOM           =  ?  ,"
                    + "CAPACITE       =  ?  ,"
                    + "REF_NIV =  ?  "
                    + "NB_ELEVES =  ?  "
                    + "WHERE  ID_CLASSE = ? ";
            statement = session.prepareStatement(requete);
            statement.setString(1, instance.getNom());
            statement.setInt(2, instance.getCapacite());
            statement.setInt(3, instance.getRef_niv());
            statement.setInt(4, instance.getNbE());
            statement.setInt(5, instance.getId_c());
            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : ClasseDAO.java\n"
                    + "Methode : update(Classe instance)\n"
                    + "Exception : " + exception);
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

    
    private int seq_id_next(){
        try {
            requete = "SELECT " +nomSequence+ ".nextval FROM DUAL";
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                seq=resultat.getInt("NEXTVAL");
            }

        } catch (Exception exception) {
            System.out.println("Classe : ClasseDAO.java\n"
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
            System.out.println("Classe : ClasseDAO.java\n"
                    + "Methode : seq_id_curr\n"
                    + "Exception : " + exception);
        }
       
        System.out.println("sequence curr  "+seq);
        return seq;
    }
    
    public void mettre_a_jour_nb_eleves(int id){
            String requete = "SELECT COUNT(*) AS NBR FROM APPARTIENT WHERE REF_C ="+id ;
            try {
                PreparedStatement ps = session.prepareStatement(requete);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    int nbr=rs.getInt("NBR");
                    String req2 = "UPDATE CLASSE SET NB_ELEVES="+nbr+" WHERE ID_CLASSE="+id;
                    PreparedStatement ps2 = session.prepareStatement(req2);
                    ps2.executeUpdate();
                    System.out.println("Mis à jour classe="+id+" à "+nbr);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(AppartientDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    
    
}