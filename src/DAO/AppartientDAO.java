/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Classe;
import ODB.OracleDBSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class AppartientDAO implements DAO<Classe> {
    
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
    
    
    
    public AppartientDAO(){
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
                classe.setNom(resultat.getString("NOMC"));
                classe.setCapacite(resultat.getInt("CAPACITE"));
                classe.setRef_niv(resultat.getInt("REF_NIV")); 
                classe.setNbE(countEleves(classe.getId_c()));
                liste.add(classe);

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int create(Classe instance) {
        seq =-1 ;
        try {
            requete = "INSERT INTO " + nomTable + " (ID_CLASSE , NOMC , CAPACITE , REF_NIV )  "
                      + "  VALUES ( " + seq_id_next() + " , ? , ? , ? )";
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
                classe.setNom(resultat.getString("NOMC"));
                classe.setCapacite(resultat.getInt("CAPACITE"));
                classe.setRef_niv(resultat.getInt("REF_NIV"));
                classe.setNbE(countEleves(classe.getId_c()));
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
                    + "NOMC           =  ?  ,"
                    + "CAPACITE       =  ?  ,"
                    + "REF_NIV =  ?  "
                    + "WHERE  ID_CLASSE = ? ";
            statement = session.prepareStatement(requete);
            statement.setString(1, instance.getNom());
            statement.setInt(2, instance.getCapacite());
            statement.setInt(3, instance.getRef_niv());
            statement.setInt(4, instance.getId_c());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    public int countEleves(int id){
        int nbE=0;
    try {
            requete2 = "SELECT COUNT(DISTINCT ID_ELEVE) AS "+"NBELEVES"+" FROM ELEVE WHERE REF_C="+id;
            statement2 = session.prepareStatement(requete2);
            resultat2 = statement2.executeQuery();
            while (resultat2.next()) {
                nbE=resultat2.getInt("NBELEVES");
            }

        } catch (Exception exception) {
            System.out.println("Classe : ClasseDAO.java\n"
                    + "Methode : countEleves\n"
                    + "Exception : " + exception);
        }
       
        System.out.println("Nombre d'eleves  "+nbE);
        return nbE;
    }
    
    
    
}
