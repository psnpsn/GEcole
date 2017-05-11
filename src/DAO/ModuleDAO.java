
package DAO;
import Models.Module;
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

public class ModuleDAO implements DAO<Module> {
    
    private String            nomTable    = "MODULE"    ;
    private String            nomSequence = "SEQ_ID_M" ;
    private String            requete     = ""         ;
    private Connection        session     = null       ;
    private PreparedStatement statement   = null       ;
    private ResultSet         resultat    = null       ;
    private boolean           valide      = false      ;
    private int               seq         =-1          ;

    
    public ModuleDAO(){
      session = OracleDBSingleton.getSession();
    }
    
    @Override
    public ObservableList<Module> getAll() {
        ArrayList<Module> liste = new ArrayList<Module>();

        try {
            requete = "SELECT * FROM Module";
            statement = session.prepareStatement(requete);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                Module mat = new Module();
                mat.setId(resultat.getInt("ID_Module"));
                mat.setNom(resultat.getString("NOM"));
                mat.setRef_niv(resultat.getInt("REF_NIV"));
                liste.add(mat);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ModuleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Module> list = FXCollections.observableArrayList(liste);
        return list;

    }

    @Override
    public boolean delAll() {
       valide = false;
        try {
            requete = "DELETE FROM Module";
            statement = session.prepareStatement(requete);
           if ( statement.executeUpdate()!=0)
                valide = true;
        } catch (SQLException ex) {
            Logger.getLogger(ModuleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valide;
    }

    @Override
    public int create(Module instance) {
        try {
            requete = "INSERT INTO Module (ID_Module , NOM , REF_NIV ) " +
                             " VALUES ( SEQ_ID_MODULE.NEXTVAL , ? , ?)";
            statement = session.prepareStatement(requete);
            statement.setString(1, instance.getNom());
            statement.setInt(2, instance.getRef_niv());
            if (statement.executeUpdate() != 0) {
                    requete = "Select ID_MODULE from MODULE where rowid=(select max(rowid) from MODULE )";
                    statement = session.prepareStatement(requete);
                    ResultSet resultat = statement.executeQuery();
                    while (resultat.next()) {
                        return resultat.getInt("ID_MODULE");
                    }
                }
                
        } catch (SQLException ex) {
            Logger.getLogger(ModuleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

  

    @Override
    public boolean delete(int id) {
       valide = false;
        try {
            requete = "DELETE FROM Module WHERE ( ID_Module= ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0){
                valide = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModuleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valide;
    }

    @Override
    public boolean update(Module instance) {
        valide = false;
        try {
            requete = "UPDATE Module SET   "
                    + "NOM           =  ?  ,"
                    + "REF_NIV        =  ?  "
                    + "WHERE  ID_Module     =  ? ";
            statement = session.prepareStatement(requete);

            statement.setString(1, instance.getNom());
            statement.setInt(2, instance.getRef_niv());
            statement.setInt(3, instance.getId());

            if(statement.executeUpdate()!=0){
                valide = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModuleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valide;
    }

    @Override public Module find(int id) {
        Module mat = null;
        try {
            requete = "SELECT * FROM MODULE WHERE ( ID_MODULE = ? )";
            statement = session.prepareStatement(requete);
            statement.setInt(1, id);
            resultat = statement.executeQuery();
            while (resultat.next()) {
                valide = true;
                mat = new Module();
                mat.setId(resultat.getInt("ID_MODULE"));
                mat.setNom(resultat.getString("NOM"));
                mat.setRef_niv(resultat.getInt("REF_NIV"));
            }
        }catch (SQLException ex) {
            Logger.getLogger(ModuleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mat;
    }
}
