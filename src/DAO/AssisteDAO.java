/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Assiste;
import ODB.OracleDBSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class AssisteDAO implements DAO<Assiste> {
    
    private String            nomTable    = "ASSISTE"    ;
    private String            nomSequence = "SEQ_ID_AS" ;
    private String            requete     = ""         ;
    private Connection        session     = null       ;
    private PreparedStatement statement   = null       ;
    private ResultSet         resultat    = null       ;
    private boolean           valide      = false      ;
    private int               seq         =-1          ;
    
     public AssisteDAO(){
      session = OracleDBSingleton.getSession();
    }

    @Override
    public ObservableList<Assiste> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int create(Assiste instance) {
        seq=-1;
        try {
            requete = "INSERT INTO " + nomTable + " (ID_ASSISTE , REF_I , REF_C , REF_M)  "
                      + "  VALUES ( " + seq_id_next() + " , ? , ? , ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, instance.getRef_i());
            statement.setInt(2, instance.getRef_c());
            statement.setString(3, instance.getRef_m());
            if (statement.executeUpdate() != 0) {
                seq=seq_id_curr();
            }
        } catch (Exception exception) {
            System.out.println("Classe : AssisteDAO.java\n"
                    + "Methode : create(Assiste instance)\n"
                    + "Exception : " + exception);
        }
        
        return seq;
    }

    @Override
    public Assiste find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Assiste instance) {
        valide = false;
        try {
            requete = "UPDATE " + nomTable + " SET   "
                    + "REF_I           =  ?  ,"
                    + "REF_C        =  ?  ,"
                    + "REF_M =  ?  "
                    + "WHERE  ID_ASSISTE = ? ";
            statement = session.prepareStatement(requete);
            statement.setInt(1, instance.getRef_i());
            statement.setInt(2, instance.getRef_c());
            statement.setString(3, instance.getRef_m());
            statement.setInt(4, instance.getId_assiste());
            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : AssisteDAO.java\n"
                    + "Methode : update(Assiste instance)\n"
                    + "Exception : " + exception);
        }
        return valide;
    }

    @Override
    public boolean delete(int id) {
        valide = false;
        try {
            requete = "DELETE FROM " + nomTable + " WHERE ( ID_ASSISTE = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0){
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : AssisteDAO.java\n"
                    + "Methode : delete(id)\n"
                    + "Exception : " + exception);
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
            System.out.println("Classe : ParentDAO.java\n"
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
            System.out.println("Classe : ParentDAO.java\n"
                    + "Methode : seq_id_curr\n"
                    + "Exception : " + exception);
        }
       
        System.out.println("sequence curr  "+seq);
        return seq;
    }
    
}
