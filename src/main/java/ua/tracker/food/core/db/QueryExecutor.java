package ua.tracker.food.core.db;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tracker.food.controller.LoginServlet;

import java.sql.*;
import java.util.List;

public class QueryExecutor{
    private Logger log = LogManager.getLogger(QueryExecutor.class);
    private StatementExecutor statementExecutor = new StatementExecutor();

    private PreparedStatement prepareStatement(Connection conn, String sql,int statementType, Object ... parameters ) throws SQLException{
        PreparedStatement statement = conn.prepareStatement(sql,statementType);
        for (int parameterNumber = 1; parameterNumber <= parameters.length; parameterNumber++) {
            statement.setObject(parameterNumber,parameters[parameterNumber-1]);
        }
        return statement;
    }

    private PreparedStatement prepareBatchStatement(Connection conn, String sql,int statementType, List<Object[]>  items ) throws SQLException{
        PreparedStatement statement = conn.prepareStatement(sql,statementType);
        for(Object[] parameters : items) {
            for (int parameterNumber = 1; parameterNumber <= parameters.length; parameterNumber++) {
                statement.setObject(parameterNumber, parameters[parameterNumber - 1]);
            }
            statement.addBatch();
        }
        return statement;
    }

    public ResultSet executeSqlQuery(Connection conn, String sql, Object ... parameters ) throws SQLException{
        log.log(Level.INFO,"Executing query '{}' with parameters {}", sql , parameters);
        PreparedStatement statement = prepareStatement(conn,sql,Statement.NO_GENERATED_KEYS,parameters);
        return statementExecutor.executeStatement(statement);
    }

    public int executeSqlUpdateAffectedRowsCount(Connection conn, String sql, Object ... parameters ) throws SQLException{
        try(PreparedStatement statement = prepareStatement(conn,sql,Statement.NO_GENERATED_KEYS,parameters)) {
            return statementExecutor.executeUpdateStatementAffectedRowsCount(statement);
        }
    }

    public ResultSet executeSqlUpdateAffectedIds(Connection conn, String sql, Object ... parameters ) throws SQLException{
        PreparedStatement statement = prepareStatement(conn,sql,Statement.RETURN_GENERATED_KEYS,parameters);
        return statementExecutor.executeUpdateStatementAffectedIds(statement);
    }

    public ResultSet executeSqlQuery(Connection connection, String sql, List<Object[]>  items ) throws SQLException{
        connection.setAutoCommit(false);
        PreparedStatement statement = prepareBatchStatement(connection,sql,Statement.NO_GENERATED_KEYS,items);
        ResultSet resultSet = statementExecutor.executeBatchStatement(statement);
        connection.commit();
        connection.setAutoCommit(true);
        return resultSet;
    }

    public int executeSqlUpdateAffectedRowsCount(Connection connection, String sql, List<Object[]>  items ) throws SQLException{
        connection.setAutoCommit(false);
        try(PreparedStatement statement = prepareBatchStatement(connection,sql,Statement.NO_GENERATED_KEYS,items)) {
            int affectedRows = statementExecutor.executeUpdateBatchStatementAffectedRowsCount(statement);
            connection.commit();
            connection.setAutoCommit(true);
            return affectedRows;
        }

    }

    public ResultSet executeSqlUpdateAffectedIds(Connection connection, String sql, List<Object[]>  items ) throws SQLException{
        connection.setAutoCommit(false);
        PreparedStatement statement = prepareBatchStatement(connection,sql,Statement.RETURN_GENERATED_KEYS,items);
        ResultSet resultSet = statementExecutor.executeUpdateBatchStatementAffectedIds(statement);
        connection.commit();
        connection.setAutoCommit(true);
        return resultSet;
    }


}
