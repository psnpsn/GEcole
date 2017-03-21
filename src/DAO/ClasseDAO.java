/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Classe;
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
public class ClasseDAO implements DAO<Classe> {
    
     private String            nomTable    = "CLASSE"    ;
    private String            nomSequence = "SEQ_ID_C.NEXTVAL" ;
    private String            requete     = ""         ;
    private Connection        session     = null       ;
    private PreparedStatement statement   = null       ;
    private ResultSet         resultat    = null       ;
    private boolean           valide      = false      ;

    @Override
    public ObservableList<Classe> getAll() {
        ObservableList<Classe> liste = FXCollections.observableArrayList();
        try {
            requete = "SELECT * FROM " + nomTable ;
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                Classe classe = new Classe();
                classe.setId_c(resultat.getInt("ID_ELEVE"));
                classe.setNom(resultat.getString("NOM"));
                classe.setCapacite(resultat.getInt("CAPACITE"));
                classe.setRef_niv(resultat.getString("REF_NIV")); 
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
    public boolean create(Classe instance) {
        valide = false;
        try {
            requete = "INSERT INTO " + nomTable + " (ID_CLASSE , NOMC , CAPACITE , REF_NIV )  "
                      + "  VALUES ( " + nomSequence + " , ? , ? , ? )";
            statement = session.prepareStatement(requete);
            statement.setString(1, instance.getNom());
            statement.setInt(2, instance.getCapacite());
            statement.setString(3, instance.getRef_niv());
            


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
    public Classe find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Classe instance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
