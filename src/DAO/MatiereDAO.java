/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Matiere;
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
public class MatiereDAO implements DAO<Matiere> {
    
    private String            nomTable    = "MATIERE"    ;
    private String            nomSequence = "SEQ_ID_M" ;
    private String            requete     = ""         ;
    private Connection        session     = null       ;
    private PreparedStatement statement   = null       ;
    private ResultSet         resultat    = null       ;
    private boolean           valide      = false      ;
    private int               seq         =-1          ;

    
    public MatiereDAO(){
      session = OracleDBSingleton.getSession();
    }
    
    @Override
    public ObservableList<Matiere> getAll() {
        ArrayList<Matiere> liste = new ArrayList<Matiere>();

            try {
                requete = "SELECT * FROM MATIERE";
                statement = session.prepareStatement(requete);
                resultat = statement.executeQuery();
                while (resultat.next()) {
                    Matiere mat = new Matiere();
                    mat.setId_m(resultat.getInt("ID_MATIERE"));
                    mat.setNom(resultat.getString("NOM"));
                    mat.setCoef(resultat.getFloat("COEF"));
                    mat.setDesc(resultat.getString("DESCRIPTION"));
                    liste.add(mat);
                }
                
            } catch (Exception ex) {
                System.out.println(ex);
            }
            ObservableList<Matiere> list = FXCollections.observableArrayList(liste);
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
        } catch (Exception exception) {
            System.out.println("Classe : MatiereDAO.java\n"
                    + "Methode : delAll()\n"
                    + "Exception : " + exception);
        }
        return valide;
    }

    @Override
    public int create(Matiere instance) {
        
        try {
            
            requete = "INSERT INTO Matiere (ID_MATIERE , NOM , COEF , DESCRIPTION) " +
                             " VALUES ( " + seq_id_next() + " , ? , ? , ?)";
            statement = session.prepareStatement(requete);
            statement.setString(1, instance.getNom());
            statement.setFloat(2, instance.getCoef());
            statement.setString(3, instance.getDesc());
            if (statement.executeUpdate() != 0) {
                seq=seq_id_curr();
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(SalleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seq;
    }

  

    @Override
    public boolean delete(int id) {
       valide = false;
        try {
            requete = "DELETE FROM " + nomTable + " WHERE ( ID_MATIERE= ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0){
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : MatiereDAO.java\n"
                    + "Methode : delete(matiere instance)\n"
                    + "Exception : " + exception);
        }
        return valide;
    }

    @Override
    public boolean update(Matiere instance) {
        valide = false;
        try {
            requete = "UPDATE " + nomTable + " SET   "
                    + "NOM           =  ?  ,"
                    + "COEF        =  ?  ,"
                    + "DESCRIPTION     =  ? "
                    + "WHERE  ID_MATIERE     =  ? ";
            statement = session.prepareStatement(requete);

            statement.setString(1, instance.getNom());
            statement.setFloat(2, instance.getCoef());
            statement.setString(3,instance.getDesc());
            statement.setInt(4, instance.getId_m());

            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : MatiereDAO.java\n"
                    + "Methode : update(matiere instance)\n"
                    + "Exception : " + exception);
        }
        return valide;
    }

    @Override
    public Matiere find(int id) {
        Matiere mat = null;
        try {
            requete = "SELECT * FROM " + nomTable +" WHERE ( ID_MATIERE = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                valide = true;
                mat = new Matiere();
                mat.setId_m(resultat.getInt("ID_MATIERE"));
                mat.setNom(resultat.getString("NOM"));
                mat.setCoef(resultat.getFloat("COEF"));
                mat.setDesc(resultat.getString("DESCRIPTION"));
                
            }

        } catch (Exception exception) {
            System.out.println("Classe : MatiereDAO.java\n"
                    + "Methode : findByID()\n"
                    + "Exception : " + exception);
        }
        return mat;
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
            System.out.println("Classe : MatiereDAO.java\n"
                    + "Methode : seq_id_next\n"
                    + "Exception : " + exception);
        }
        System.out.println("sequence nextval "+seq);
        return seq;
    }

    public int seq_id_curr(){
    try {
            String requete = "SELECT " +nomSequence+ ".currval FROM DUAL";
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                seq=resultat.getInt("CURRVAL");
            }

        } catch (Exception exception) {
            System.out.println("Classe : MatiereDAO.java\n"
                    + "Methode : seq_id_curr\n"
                    + "Exception : " + exception);
        }

        System.out.println("sequence curr  "+seq);
        return seq;
    }
    
}
