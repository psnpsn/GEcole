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
    private String            nomSequence = "SEQ_ID_E" ;
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
                eleve.setId_e(resultat.getInt("IDENTIFIANT"));
                eleve.setNom(resultat.getString("NOM"));
                eleve.setPrenom(resultat.getString("PRENOM"));
                eleve.setAdresse(resultat.getString("ADDRESSE"));
                eleve.setVille(resultat.getString("VILLE"));
                eleve.setCodeP(resultat.getInt("CODEPOSTAL"));
                eleve.setDateNaiss(resultat.getDate("DATENAISSANCE"));
                eleve.setLieuNaiss(resultat.getString("LIEUNAISSANCE"));
                eleve.setSex(resultat.getString("SEXE"));
                eleve.setTel(resultat.getInt("TEL_DOMICILE"));
                eleve.setTel2(resultat.getInt("TEL_PORTABLE"));
                eleve.setEmail(resultat.getString("EMAIL"));
                eleve.setRef_niv(resultat.getInt("REF_NIVEAU"));
                eleve.setRef_c(resultat.getInt("REF_CLASSE"));
                eleve.setRef_p(resultat.getInt("REF_PARENTS"));
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
        return false;
    }

    @Override
    public boolean create(Eleve instance) {
        return false;
    }

    @Override
    public Eleve find(int id) {
        return null;
    }

    @Override
    public boolean update(Eleve instance) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

}
