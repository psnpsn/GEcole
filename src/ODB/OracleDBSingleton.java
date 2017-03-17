package ODB;

import java.sql.Connection;
import java.sql.DriverManager;

//
//There are two forms of singleton design pattern
//
//  Early Instantiation: creation of instance at load time.
//  Lazy Instantiation : creation of instance when required. (X)
//
// Class Singleton simple

public class OracleDBSingleton {
    static String username       ;
    static String password       ;
    static String url            ;
    static String driver         ;
    static Connection connection ;

    private OracleDBSingleton() {

    }
    
    // configure Oracle avant connection
    public static void setup(String username,String password /*,String driver,String url */ )
    {
        OracleDBSingleton.connection = null;
        OracleDBSingleton.url = "jdbc:oracle:thin:@localhost:1521:xe";
        OracleDBSingleton.driver = "oracle.jdbc.driver.OracleDriver";
        OracleDBSingleton.username = username;
        OracleDBSingleton.password = password;
        // driver et url ? maybe... soon
    }
    
    public static Connection getSession() throws Exception {
        if (OracleDBSingleton.connection == null) {
            Class.forName(driver).newInstance();
            OracleDBSingleton.connection = DriverManager.getConnection(url, username, password);
        }
        return OracleDBSingleton.connection;
    }
}
