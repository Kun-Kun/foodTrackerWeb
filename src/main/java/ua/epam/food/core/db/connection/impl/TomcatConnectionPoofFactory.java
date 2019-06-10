package ua.epam.food.core.db.connection.impl;

import ua.epam.food.core.db.connection.ConnectionFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TomcatConnectionPoofFactory implements ConnectionFactory {

    private DataSource dataSource = null;
    private static TomcatConnectionPoofFactory _instance  = null;

    private TomcatConnectionPoofFactory() {}

    @Override
    public Connection getConnection() throws SQLException {
        if (dataSource==null){
            initializeDatasource();
        }
        return dataSource.getConnection();
    }

    public static synchronized TomcatConnectionPoofFactory getInstance() {
        if (_instance == null)
            _instance = new TomcatConnectionPoofFactory();
        return _instance;
    }

    private synchronized void initializeDatasource() {
        if (dataSource == null) {
            try {
                InitialContext initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/foodTrackerWeb");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

}
