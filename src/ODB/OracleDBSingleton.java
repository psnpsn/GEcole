package ODB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OracleDBSingleton {
    private static String username;
    private static String password;
    private static String url ;
    private static String driver = "";
    private static String sql_cmd = "";
    private static Connection session;

    public static void seDeconnecter() {
        try {
            if (OracleDBSingleton.getSession() != null) {
                OracleDBSingleton.getSession().close();
                System.out.println("Session Oracle Terminer.");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur Terminaison Session Oracle. :" + ex.getMessage());
        }
    }

    private OracleDBSingleton() {

    }
    public static boolean seConnecter(String url,String driver ,String username, String password) {
        boolean ok = false;
        sql_cmd = "";
        OracleDBSingleton.username = username;
        OracleDBSingleton.password = password;
        OracleDBSingleton.url = url;
        OracleDBSingleton.driver = driver;
        if (OracleDBSingleton.session == null) {
            OracleDBSingleton.getSession();
        }
        if (OracleDBSingleton.session != null) {
            sql_cmd = "SELECT * FROM DUAL";
            try {
                PreparedStatement statement = OracleDBSingleton.getSession().prepareStatement(sql_cmd);
                ResultSet r = statement.executeQuery();
                if (r.next()) {
                    System.out.println("Connection a la BD reussie :") ;
                    return true;
                }
                return false;
            } catch (SQLException ex) {
               Logger.getLogger(OracleDBSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public static Connection getSession() {
        if (OracleDBSingleton.session == null) {
            try {
                Class.forName(driver).newInstance();
                OracleDBSingleton.session = DriverManager.getConnection(OracleDBSingleton.url, OracleDBSingleton.username, OracleDBSingleton.password);
            } catch (Exception exception) {
                System.out.println("Classe : OracleDBSingleton.java\n"
                        + "Methode :getSession()\n"
                        + "Exception : " + exception);
            }
            return OracleDBSingleton.session;
        } else {
            return OracleDBSingleton.session;
        }
    }

    public static int inst(int cin){
        String requete = "SELECT ID_INST FROM INST WHERE NCIN = " + cin ;
        try {
            PreparedStatement ps = OracleDBSingleton.getSession().prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return rs.getInt("ID_INST");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OracleDBSingleton.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return -1;
    }
}
