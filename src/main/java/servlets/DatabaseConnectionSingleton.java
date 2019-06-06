package servlets;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionSingleton {
    private static DatabaseConnectionSingleton _instance = null;

    private DatabaseConnectionSingleton() {}

    public static synchronized DatabaseConnectionSingleton getInstance() {
        if (_instance == null)
            _instance = new DatabaseConnectionSingleton();
        return _instance;
    }


    /**
     * JDBC Driver and database url
     */
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/tracker?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    /**
     * User and Password
     */
    private static final String USER = "root";
    private static final String PASSWORD = "Admin111";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    private Connection connection = null;

    private void initializeDatabaseConnection(){
    /*    System.out.println("Registering JDBC driver...");
        Class.forName(JDBC_DRIVER);

        System.out.println("Creating database connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);*/

    }

}
