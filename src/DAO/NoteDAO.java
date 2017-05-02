/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Note;
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
public class NoteDAO implements DAO<Note> {
    
    private String            nomTable    = "NOTE"    ;
    private String            nomSequence = "SEQ_ID_N" ;
    private String            requete     = ""         ;
    private Connection        session     = null       ;
    private PreparedStatement statement   = null       ;
    private ResultSet         resultat    = null       ;
    private boolean           valide      = false      ;
    private int               seq         =-1          ;

    
    public NoteDAO(){
      session = OracleDBSingleton.getSession();
    }
    
    @Override
    public ObservableList<Note> getAll() {
        ArrayList<Note> liste = new ArrayList<Note>();

            try {
                requete = "SELECT * FROM "+nomTable;
                statement = session.prepareStatement(requete);
                resultat = statement.executeQuery();
                while (resultat.next()) {
                    Note note= new Note();
                    note.setId_note(resultat.getInt("ID_NOTE"));
                    note.setNote(resultat.getFloat("NOTE"));
                    note.setType(resultat.getString("TYPE"));
                    note.setRef_ass(resultat.getInt("REF_AS"));
                    note.setRef_e(resultat.getInt("REF_E"));
                    liste.add(note);
                }
                
            } catch (Exception ex) {
                System.out.println(ex);
            }
            ObservableList<Note> list = FXCollections.observableArrayList(liste);
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
            System.out.println("Classe : NoteDAO.java\n"
                    + "Methode : delAll()\n"
                    + "Exception : " + exception);
        }
        return valide;
    }

    @Override
    public int create(Note instance) {
        
        try {
            
            requete = "INSERT INTO NOTE (ID_NOTE , NOTE , TYPE , REF_AS , REF_E) " +
                             " VALUES ( " + seq_id_next() + " , ? , ? , ?, ?)";
            statement = session.prepareStatement(requete);
            statement.setFloat(1, instance.getNote());
            statement.setString(2, instance.getType());
            statement.setInt(3, instance.getRef_ass());
            statement.setInt(4, instance.getRef_e());
            if (statement.executeUpdate() != 0) {
                seq=seq_id_curr();
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(NoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seq;
    }

  

    @Override
    public boolean delete(int id) {
       valide = false;
        try {
            requete = "DELETE FROM " + nomTable + " WHERE ( ID_NOTE= ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0){
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : NoteDAO.java\n"
                    + "Methode : delete(matiere instance)\n"
                    + "Exception : " + exception);
        }
        return valide;
    }

    @Override
    public boolean update(Note instance) {
        valide = false;
        try {
            requete = "UPDATE " + nomTable + " SET   "
                    + "NOTE           =  ?  ,"
                    + "TYPE        =  ?  ,"
                    + "REF_AS     =  ?, "
                    + "REF_E     =  ? "
                    + "WHERE  ID_NOTE     =  ? ";
            statement = session.prepareStatement(requete);

            statement.setString(2, instance.getType());
            statement.setFloat(1, instance.getNote());
            statement.setInt(3,instance.getRef_ass());
            statement.setInt(4, instance.getRef_e());
            statement.setInt(4, instance.getId_note());

            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : NoteDAO.java\n"
                    + "Methode : update(matiere instance)\n"
                    + "Exception : " + exception);
        }
        return valide;
    }

    @Override
    public Note find(int id) {
        Note note= null;
        try {
            requete = "SELECT * FROM " + nomTable +" WHERE ( ID_NOTE = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                valide = true;
                note= new Note();
                note.setId_note(resultat.getInt("ID_NOTE"));
                note.setType(resultat.getString("TYPE"));
                note.setNote(resultat.getFloat("NOTE"));
                note.setRef_ass(resultat.getInt("REF_AS"));
                note.setRef_e(resultat.getInt("REF_E"));
                
            }

        } catch (Exception exception) {
            System.out.println("Classe : NoteDAO.java\n"
                    + "Methode : findByID()\n"
                    + "Exception : " + exception);
        }
        return note;
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
            System.out.println("Classe : NoteDAO.java\n"
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
            System.out.println("Classe : NoteDAO.java\n"
                    + "Methode : seq_id_curr\n"
                    + "Exception : " + exception);
        }

        System.out.println("sequence curr  "+seq);
        return seq;
    }
    
}
