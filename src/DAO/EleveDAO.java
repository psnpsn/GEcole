package DAO;
//Eleve Oracle DB DAO

import Models.Eleve;
import ODB.OracleDBSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EleveDAO implements DAO<Eleve> {

    private String            nomTable    = "ELEVE"    ;
    private String            nomSequence = "SEQ_ID_E.NEXTVAL" ;
    private String            requete     = ""         ;
    private Connection        session     = null       ;
    private PreparedStatement statement   = null       ;
    private ResultSet         resultat    = null       ;
    private boolean           valide      = false      ;


    public EleveDAO(){
      session = OracleDBSingleton.getSession();
    }

    @Override
    public ArrayList<Eleve> getAll() {
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
                eleve.setAdresse(resultat.getString("ADRESSE"));
                eleve.setVille(resultat.getString("VILLE"));
                eleve.setCodeP(resultat.getInt("CODEP"));
                eleve.setDateNaiss(resultat.getDate("DATENAISS"));
                eleve.setLieuNaiss(resultat.getString("LIEUNAISS"));
                eleve.setSex(resultat.getString("SEX"));
                eleve.setEmail(resultat.getString("EMAIL"));
                eleve.setRef_niv(resultat.getInt("REF_NIV"));
                eleve.setRef_c(resultat.getInt("REF_C"));
                eleve.setRef_p(resultat.getInt("REF_P"));
                eleve.setDateIns(resultat.getDate("DATEINS"));
               // eleve.set(resultat.getDate("DATEINSCRIPTION"));
                liste.add(eleve);

            }
        } catch (Exception exception) {
            System.out.println("Classe : EleveDAO.java\n"
                    + "Methode : getAll()\n"
                    + "Exception : " + exception);
        }
        return liste;
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
            System.out.println("Classe : EleveDAO.java\n"
                    + "Methode : delAll()\n"
                    + "Exception : " + exception);
        }
        return valide;
    }

    @Override
    public boolean create(Eleve instance) {
    valide = false;
        try {
            requete = "INSERT INTO " + nomTable + " (ID_ELEVE , NOM , PRENOM , ADRESSE , VILLE , CODEP , DATENAISS , LIEUNAISS , SEX , EMAIL , REF_NIV , REF_P ,DATEINS)  "
                      + "  VALUES ( " + nomSequence + " , ? , ? , ? , ? , ? , ? , ? , ? , ? , 12016 , 1 , TO_DATE(SYSDATE, 'DD-MM-YYYY') )";
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
            //statement.setInt(10, instance.getRef_niv());
            //statement.setInt(11, instance.getRef_c());
            //statement.setInt(12, instance.getRef_p());
            if (statement.executeUpdate() != 0) {
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : EleveDAO.java\n"
                    + "Methode : create(Eleve instance)\n"
                    + "Exception : " + exception);
        }
        return valide;
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
                eleve.setDateNaiss(resultat.getDate("DATENAISS"));
                eleve.setLieuNaiss(resultat.getString("LIEUNAISS"));
                eleve.setSex(resultat.getString("SEX"));
                eleve.setEmail(resultat.getString("EMAIL"));
                eleve.setRef_niv(resultat.getInt("REF_NIV"));
                eleve.setRef_c(resultat.getInt("REF_C"));
                eleve.setRef_p(resultat.getInt("REF_P"));
            }

        } catch (Exception exception) {
            System.out.println("Classe : EleveDAO.java\n"
                    + "Methode : findByID()\n"
                    + "Exception : " + exception);
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
                    + "DATENAISS =  ?  ,"
                    + "LIEUNAISS =  ?  ,"
                    + "SEX          =  ?  ,"
                    + "EMAIL  =  ?  ,"
                    + "REF_NIV    =  ?  ,"
                    + "REF_C    =  ?  ,"
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
            statement.setInt(10, instance.getRef_niv());
            statement.setInt(11, instance.getRef_c());
            statement.setInt(12, instance.getRef_p());
            statement.setInt(13, instance.getId_e());

            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : EleveDAO.java\n"
                    + "Methode : update(Eleve instance)\n"
                    + "Exception : " + exception);
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
        } catch (Exception exception) {
            System.out.println("Classe : EleveDAO.java\n"
                    + "Methode : delete(Eleve instance)\n"
                    + "Exception : " + exception);
        }
        return valide;
    }

}
