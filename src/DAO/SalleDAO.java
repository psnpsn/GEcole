package DAO;

import Models.Salle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class SalleDAO implements DAO<Salle> {

    @Override
    public ObservableList<Salle> getAll() {
            ArrayList<Salle> liste = new ArrayList<Salle>();

            try {
                String requete = "SELECT * FROM SALLE";
                Connection session = ODB.OracleDBSingleton.getSession();
                PreparedStatement statement = session.prepareStatement(requete);
                ResultSet resultat = statement.executeQuery();
                while (resultat.next()) {
                    Salle salle = new Salle();
                    salle.setIdentifiant(resultat.getInt("ID_SALLE"));
                    salle.setType_salle(resultat.getString("NOM"));
                    salle.setCapacite(resultat.getInt("CAPACITE"));
                    liste.add(salle);
                }
                ObservableList<Salle> list = FXCollections.observableArrayList(liste);
                return list;
            } catch (Exception ex) {
                System.out.println(ex);
            }
            return null;
    }

    @Override
    public boolean delAll() {
        String requete = "DELETE FROM SALLE";
        try {
            Connection session = ODB.OracleDBSingleton.getSession();
            PreparedStatement statement = session.prepareStatement(requete);
            statement = session.prepareStatement(requete);
            return (statement.executeUpdate() > 0);
        } catch (Exception exception) {
            System.out.println("exception delete salle :" + exception);
        }
        return false;
    }

    @Override
    public int create(Salle instance) {
        try {
            String requete = "INSERT INTO Salle (ID_SALLE , TYPE , CAPACITE , DATEC) " +
                             " VALUES ( SEQ_ID_S.NEXTVAL , ? , ? , ?)";
            Connection session = ODB.OracleDBSingleton.getSession();
            PreparedStatement statement = session.prepareStatement(requete);
            statement.setString(1, ((Salle) instance).getType_salle());
            statement.setInt(2, ((Salle) instance).getCapacite());
            statement.setDate(3, new java.sql.Date(instance.getDate_creation().getTime()));
            if (statement.executeUpdate() != 0) {
                    requete = "Select ID_SALLE from SALLE where rowid=(select max(rowid) from SALLE )";
                    statement = session.prepareStatement(requete);
                    ResultSet resultat = statement.executeQuery();
                    while (resultat.next()) {
                        return resultat.getInt("ID_SALLE");
                    }
                }
        } catch (SQLException ex) {
            Logger.getLogger(SalleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public Salle find(int id) {
        try {
            String requete = "SELECT * FROM SALLE WHERE  ID_SALLE = ? ";
            Connection session = ODB.OracleDBSingleton.getSession();
            PreparedStatement statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            ResultSet resultat = statement.executeQuery();
                while (resultat.next()) {
                    Salle c = new Salle();
                    c.setIdentifiant(resultat.getInt("ID_SALLE"));
                    c.setType_salle(resultat.getString("TYPE"));
                    c.setCapacite(resultat.getInt("CAPACITE"));
                    c.setDate_creation(resultat.getDate("DATEC"));
                    return c;
                }
        } catch (SQLException ex) {
            Logger.getLogger(SalleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean update(Salle instance) {
        String  requete = "UPDATE SALLE SET   "
                    + "NOM       =  ?  ,"
                    + "TYPE    =  ?  ,"
                    + "DATEC       =  ?"
                    + "WHERE  ID_SALLE = ? ";
        try {
            Connection session = ODB.OracleDBSingleton.getSession();
            PreparedStatement statement = session.prepareStatement(requete);
                statement = session.prepareStatement(requete);
                statement.setString(1, ((Salle) instance).getType_salle());
                statement.setInt(2, ((Salle) instance).getCapacite());
                statement.setDate(3,new java.sql.Date(instance.getDate_creation().getTime()));
                statement.setInt(4, ((Salle) instance).getIdentifiant());
                return (statement.executeUpdate() != 0);
            } catch (Exception exception) {
                System.out.println("exception update salle:" + exception);
            }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            String requete = " DELETE FROM SALLE WHERE ID_SALLE = ?";
            Connection session = ODB.OracleDBSingleton.getSession();
            PreparedStatement statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            return (statement.executeUpdate() > 0);
        } catch (Exception exception) {
            System.out.println("exception suppression salle:" + exception);
        }
        return false;
    }
}