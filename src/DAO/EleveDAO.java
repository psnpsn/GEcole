package DAO;
//Eleve Oracle DB DAO

import Models.Eleve;
import ODB.OracleDBSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EleveDAO implements DAO<Eleve> {

    private final String            nomTable    = "ELEVE"    ;
    private final String            nomSequence = "SEQ_ID_E" ;
    private String            requete     = ""         ;
    private Connection        session     = null       ;
    private PreparedStatement statement   = null       ;
    private ResultSet         resultat    = null       ;
    private boolean           valide      = false      ;
    private int               seq         =-1          ;


    public EleveDAO(){
      session = OracleDBSingleton.getSession();
    }

    @Override
    public ObservableList<Eleve> getAll() {
        ArrayList<Eleve> liste = new ArrayList<Eleve>();
        try {
            requete = "SELECT * FROM " + nomTable ;
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
             while (resultat.next()) {
                Eleve eleve = new Eleve();
                eleve.setId_e(resultat.getInt("ID_ELEVE"));
                eleve.setNom(resultat.getString("NOM"));
                eleve.setPrenom(resultat.getString("PRENOM"));
                eleve.setDateNaiss(resultat.getTimestamp("DATE_NAISS"));
                eleve.setLieuNaiss(resultat.getString("LIEU_NAISS"));
                eleve.setSex(resultat.getString("SEX"));
                eleve.setAdresse(resultat.getString("ADRESSE"));
                eleve.setVille(resultat.getString("VILLE"));
                eleve.setCodeP(resultat.getInt("CODEP"));
                eleve.setEmail(resultat.getString("EMAIL"));
                eleve.setDateIns(resultat.getTimestamp("DATE_INS"));
                eleve.setRef_p(resultat.getInt("REF_P"));
                liste.add(eleve);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Eleve> list = FXCollections.observableArrayList(liste);
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
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valide;
    }

    @Override
    public int create(Eleve instance) {
    seq = -1;
        try {
            requete = "INSERT INTO " + nomTable + " (ID_ELEVE , NOM , PRENOM , ADRESSE , VILLE , CODEP , DATE_NAISS , LIEU_NAISS , SEX , EMAIL , REF_P ,DATE_INS)  "
                      + "  VALUES ( " + seq_id_next() + " , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , SYSDATE )";
            statement = session.prepareStatement(requete);
            statement.setString(1, instance.getNom());
            statement.setString(2, instance.getPrenom());
            statement.setString(3, instance.getAdresse());
            statement.setString(4, instance.getVille());
            statement.setInt(5, instance.getCodeP());
            statement.setDate(6, new java.sql.Date(instance.getDateNaiss().getTime()));
            statement.setString(7, instance.getLieuNaiss());
            statement.setString(8,instance.getSex());
            statement.setString(9, instance.getEmail());
            statement.setInt(10, instance.getRef_p());
            if (statement.executeUpdate() != 0) {
                seq = seq_id_curr();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return seq;
    }

    @Override
    public Eleve find(int id) {
    Eleve eleve = null;
        try {
            requete = "SELECT * FROM " + nomTable +" WHERE ( ID_ELEVE = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                valide = true;
                eleve = new Eleve();
                eleve.setId_e(resultat.getInt("ID_ELEVE"));
                eleve.setNom(resultat.getString("NOM"));
                eleve.setPrenom(resultat.getString("PRENOM"));
                eleve.setAdresse(resultat.getString("ADRESSE"));
                eleve.setVille(resultat.getString("VILLE"));
                eleve.setCodeP(resultat.getInt("CODEP"));
                eleve.setDateNaiss(resultat.getTimestamp("DATE_NAISS"));
                eleve.setLieuNaiss(resultat.getString("LIEU_NAISS"));
                eleve.setSex(resultat.getString("SEX"));
                eleve.setEmail(resultat.getString("EMAIL"));
                eleve.setRef_p(resultat.getInt("REF_P"));
                eleve.setDateIns(resultat.getTimestamp("DATE_INS"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eleve;
    }

    @Override
    public boolean update(Eleve instance) {
    valide = false;
        try {
            requete = "UPDATE " + nomTable + " SET   "
                    + "NOM           =  ?  ,"
                    + "PRENOM        =  ?  ,"
                    + "ADRESSE      =  ?  ,"
                    + "VILLE         =  ?  ,"
                    + "CODEP    =  ?  ,"
                    + "DATE_NAISS =  ?  ,"
                    + "LIEU_NAISS =  ?  ,"
                    + "SEX          =  ?  ,"
                    + "EMAIL  =  ?  ,"
                    + "REF_P   =  ? "
                    + "WHERE  ID_ELEVE = ? ";
            statement = session.prepareStatement(requete);
            statement.setString(1, instance.getNom());
            statement.setString(2, instance.getPrenom());
            statement.setString(3, instance.getAdresse());
            statement.setString(4, instance.getVille());
            statement.setInt(5, instance.getCodeP());
            statement.setDate(6,  new java.sql.Date(instance.getDateNaiss().getTime()));
            statement.setString(7, instance.getLieuNaiss());
            statement.setString(8, instance.getSex());
            statement.setString(9, instance.getEmail());
            statement.setInt(10, instance.getRef_p());
            statement.setInt(11, instance.getId_e());

            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valide;
    }

    @Override
    public boolean delete(int id) {
        valide = false;
        try {
            requete = "DELETE FROM " + nomTable + " WHERE ( ID_ELEVE = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0){
                valide = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valide;
    }

    public boolean updateRef_c(Eleve instance) {
        // a modifier !
    valide = false;
        try {
            requete = "UPDATE " + nomTable + " SET   "
                    + "REF_C    =  ?  "
                    + "WHERE  ID_ELEVE = ? ";
            statement = session.prepareStatement(requete);
          //  statement.setInt(1, instance.getRef_c());
            statement.setInt(2, instance.getId_e());

            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valide;
    }
    
    public boolean nullRef_c(Eleve instance) {
    valide = false;
        try {
            requete = "UPDATE " + nomTable + " SET   "
                    + "REF_C    =  NULL  "
                    + "WHERE  ID_ELEVE = ? ";
            statement = session.prepareStatement(requete);
            statement.setInt(1, instance.getId_e());

            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("sequence curr  "+seq);
        return seq;
    }
    
    public ObservableList<Eleve> getbyRef_c(int id){
        ArrayList<Eleve> liste = new ArrayList<Eleve>();
        try {
            requete = "SELECT * FROM " + nomTable+" WHERE REF_C="+id ;
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
             while (resultat.next()) {
                Eleve eleve = new Eleve();
                eleve.setId_e(resultat.getInt("ID_ELEVE"));
                eleve.setNom(resultat.getString("NOM"));
                eleve.setPrenom(resultat.getString("PRENOM"));
                eleve.setAdresse(resultat.getString("ADRESSE"));
                eleve.setVille(resultat.getString("VILLE"));
                eleve.setCodeP(resultat.getInt("CODEP"));
                eleve.setDateNaiss(resultat.getDate("DATE_NAISS"));
                eleve.setLieuNaiss(resultat.getString("LIEU_NAISS"));
                eleve.setSex(resultat.getString("SEX"));
                eleve.setEmail(resultat.getString("EMAIL"));
                eleve.setRef_p(resultat.getInt("REF_P"));
                eleve.setDateIns(resultat.getDate("DATE_INS"));
                liste.add(eleve);
            }
        }catch (SQLException ex) {
            Logger.getLogger(EleveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Eleve> list = FXCollections.observableArrayList(liste);
        return list;
    }

}
