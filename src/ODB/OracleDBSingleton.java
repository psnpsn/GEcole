package ODB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//
//There are two forms of singleton design pattern
//
//  Early Instantiation: creation of instance at load time.
//  Lazy Instantiation : creation of instance when required. (X)
//
// Class Singleton simple

public class OracleDBSingleton {
    private static String username;
    private static String password;
    private static String url ;
    private static String driver = "";
    private static String sql_cmd = "";
    private static Connection session;

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
            sql_cmd = "SELECT * FROM ELEVE";
            try {
                PreparedStatement statement = OracleDBSingleton.getSession().prepareStatement(sql_cmd);
                ResultSet r = statement.executeQuery();
                if (r.next()) {
                    System.out.println("Connection a la BD reussie :") ;
                             //  + "Username = " + username );
                    return true;
                }
                return false;
            } catch (SQLException exception) {
                System.out.println("Classe : OracleDB.java\n"
                        + "Methode :seConnecter()\n"
                        + "Exception : " + exception);
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

}
