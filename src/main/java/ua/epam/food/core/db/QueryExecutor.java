package ua.epam.food.core.db;

import ua.epam.food.exception.WrongResultException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryExecutor{

    private DataSource dataSource = null;

    public QueryExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public QueryExecutor() {
        initializeDatasource();
    }

    private synchronized void initializeDatasource(){
        if(dataSource==null) {
            try {
                InitialContext initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/foodTrackerWeb");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Map<String, Object>> findAll(String sql, Object ... parameters ){

        try(Connection conn = dataSource.getConnection()) {
            ResultSet result = executeSqlQuery(conn,sql,parameters);
            List<Map<String, Object>> resultMap = resultSetToList(result);
            return resultMap;

        }catch (SQLException ssql){
            System.out.println(ssql);
        }
        return null;
    }

    public Map<String, Object> findFirst(String sql, Object ... parameters ){

        try(Connection conn = dataSource.getConnection()) {
            ResultSet result = executeSqlQuery(conn,sql,parameters);
            Map<String, Object> resultMap = resultSetToFirstRecord(result);
            return resultMap;

        }catch (SQLException ssql){
            System.out.println(ssql);
        }
        return null;
    }

    public Map<String, Object> findOne(String sql, Object ... parameters ) throws WrongResultException{

        try(Connection conn = dataSource.getConnection()) {
            ResultSet resultSet = executeSqlQuery(conn,sql,parameters);
            if(resultSet.getFetchSize()>1){
                throw new WrongResultException();
            };
            Map<String, Object> resultMap = resultSetToFirstRecord(resultSet);
            return resultMap;

        }catch (SQLException ssql){
            System.out.println(ssql);
        }
        return null;
    }

    private PreparedStatement prepareStatement(Connection conn, String sql, Object ... parameters ) throws SQLException{
        PreparedStatement statement = conn.prepareStatement(sql);
        for (int parameterNumber = 0; parameterNumber < parameters.length; parameterNumber++) {
            statement.setObject(parameterNumber,parameters[parameterNumber]);
        }
        return statement;
    }

    private PreparedStatement prepareStatementReturnGeneratedKeys(Connection conn, String sql, Object ... parameters ) throws SQLException{
        PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        for (int parameterNumber = 0; parameterNumber < parameters.length; parameterNumber++) {
            statement.setObject(parameterNumber,parameters[parameterNumber]);
        }
        return statement;
    }

    private ResultSet executeSqlQuery(Connection conn, String sql, Object ... parameters ) throws SQLException{
        Statement statement = prepareStatement(conn,sql,parameters);
        return statement.executeQuery(sql);
    }

    private int executeSqlUpdateAffectedRowsCount(Connection conn, String sql, Object ... parameters ) throws SQLException{
        Statement statement = prepareStatement(conn,sql,parameters);
        return statement.executeUpdate(sql);
    }

    private List<Object> executeSqlUpdateAffectedIds(Connection conn, String sql, Object ... parameters ) throws SQLException{
        Statement statement = prepareStatementReturnGeneratedKeys(conn,sql,parameters);
        int affectedRows = statement.executeUpdate(sql);
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        ResultSet generatedKeys = statement.getGeneratedKeys();
        List<Object> keyList = new ArrayList<>();

        while (generatedKeys.next()) {
            keyList.add(generatedKeys.getObject(1));
        }
        return keyList;

    }


    private static List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        while (rs.next()){
            Map<String, Object> row = new HashMap<String, Object>(columns);
            for(int i = 1; i <= columns; ++i){
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            rows.add(row);
        }
        return rows;
    }

    private static Map<String, Object> resultSetToFirstRecord(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        Map<String, Object> row = null;
        if (rs.next()){
            row = new HashMap<String, Object>(columns);
            for(int i = 1; i <= columns; ++i){
                row.put(md.getColumnName(i), rs.getObject(i));
            }
        }
        return row;
    }
}
