package DAO;

import Models.Parent;
import ODB.OracleDBSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class ParentDAO implements DAO<Parent>{

    private String            nomTable    = "Parent"    ;
    private String            nomSequence = "SEQ_ID_P.NEXTVAL" ;
    private String            requete     = ""         ;
    private Connection        session     = null       ;
    private PreparedStatement statement   = null       ;
    private ResultSet         resultat    = null       ;
    private boolean           valide      = false      ;

    public ParentDAO(){
      session = OracleDBSingleton.getSession();
    }

    @Override
    public ArrayList<Parent> getAll() {
        return null;
    }

    @Override
    public boolean delAll() {
        return false;
    }

    @Override
    public boolean create(Parent instance) {
            valide = false;
        try {
            requete = "INSERT INTO " + nomTable + " (ID_PARENT , NOMP , PROFP , NOMM , PROFM , TELP , EMAILP)  "
                      + "  VALUES ( " + nomSequence + " , ? , ? , ? , ? , ? , ?  )";
            statement = session.prepareStatement(requete);
            statement.setString(1, instance.getNOMP());
            statement.setString(2, instance.getPROFP());
            statement.setString(3, instance.getNOMM());
            statement.setString(4, instance.getPROFM());
            statement.setString(5, instance.getTELP());
            statement.setString(6, instance.getEMAILP());
            if (statement.executeUpdate() != 0) {
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : ParentDAO.java\n"
                    + "Methode : create(Parent instance)\n"
                    + "Exception : " + exception);
        }
        return valide;
    }
    public int dernier() {
        int id = -1;
        try {
            requete = "Select ID_PARENT from " + nomTable + " "
                    + " where rowid=(select max(rowid) from " + nomTable + ")";
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                id = resultat.getInt("ID_PARENT");
            }
        } catch (Exception exception) {
            System.out.println("Classe : ParentDAO.java\n"
                    + "Methode : dernier(Parent instance)\n"
                    + "Exception : " + exception);
        }
        return id;
    }

    @Override
    public Parent find(int id) {
        Parent parent = null;
        try {
            requete = "SELECT * FROM " + nomTable +" WHERE ( ID_PARENT = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                valide = true;
                parent = new Parent();
                parent.setID_PARENT(resultat.getInt("ID_PARENT"));
                parent.setNOMP(resultat.getString("NOMP"));
                parent.setPROFP(resultat.getString("PROFP"));
                parent.setNOMM(resultat.getString("NOMM"));
                parent.setPROFM(resultat.getString("PROFM"));
                parent.setEMAILP(resultat.getString("EMAILP"));
                parent.setTELP(resultat.getString("TELP"));
            }

        } catch (Exception exception) {
            System.out.println("Classe : ParentDAO.java\n"
                    + "Methode : find()\n"
                    + "Exception : " + exception);
        }
        return parent;
    }

    @Override
    public boolean update(Parent instance) {
    valide = false;
        try {
            requete = "UPDATE " + nomTable + " SET   "
                    + "NOMP           =  ?  ,"
                    + "NOMM        =  ?  ,"
                    + "PROFP      =  ?  ,"
                    + "PROFM         =  ?  ,"
                    + "TELP    =  ?  ,"
                    + "EMAILP =  ?  "
                    + "WHERE  ID_PARENT = ? ";
            statement = session.prepareStatement(requete);
            statement.setString(1, instance.getNOMP());
            statement.setString(2, instance.getNOMM());
            statement.setString(3, instance.getPROFP());
            statement.setString(4, instance.getPROFM());
            statement.setString(5, instance.getTELP());
            statement.setString(6, instance.getEMAILP());
            statement.setInt(7, instance.getID_PARENT());

            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : ParentDAO.java\n"
                    + "Methode : update(Parent instance)\n"
                    + "Exception : " + exception);
        }
        return valide;

    }

    @Override
    public boolean delete(int id) {
        valide = false;
        try {
            requete = "DELETE FROM " + nomTable + " WHERE ( ID_PARENT = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0){
                valide = true;
            }
        } catch (Exception exception) {
            System.out.println("Classe : ParentDAO.java\n"
                    + "Methode : delete(id)\n"
                    + "Exception : " + exception);
        }
        return valide;
    }



}
