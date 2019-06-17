package ua.epam.food.core.db;

import org.apache.commons.dbutils.DbUtils;
import ua.epam.food.exception.ApplicationDatabaseException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudQueryExecutorService<I> {

    private DataSource dataSource = null;
    private QueryExecutor queryExecutor = new QueryExecutor();

    public CrudQueryExecutorService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CrudQueryExecutorService() {
        initializeDatasource();
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

    public List<Map<String, Object>> find(String sql, Object... parameters) {

        try (Connection conn = dataSource.getConnection();
            ResultSet result = queryExecutor.executeSqlQuery(conn, sql, parameters)){
                List<Map<String, Object>> resultList = resultSetToList(result);
                DbUtils.closeQuietly(result.getStatement());
                return resultList;

        } catch (SQLException ssql) {
            throw new ApplicationDatabaseException(ssql);
        }
    }

    public Map<String, Object> findFirst(String sql, Object... parameters) {

        try (Connection conn = dataSource.getConnection();
             ResultSet result = queryExecutor.executeSqlQuery(conn, sql, parameters)) {
                Map<String, Object> resultMap = resultSetToFirstRecord(result);
                DbUtils.closeQuietly(result.getStatement());
                return resultMap;
        } catch (SQLException ssql) {
            throw new ApplicationDatabaseException(ssql);
        }
        //return null;
    }


    public int update(String sql, Object... parameters) {
        try (Connection conn = dataSource.getConnection()) {
            return queryExecutor.executeSqlUpdateAffectedRowsCount(conn, sql, parameters);
        } catch (SQLException ssql) {
            throw new ApplicationDatabaseException(ssql);
        }
        //return 0;
    }

    public int update(String sql, List<Object[]> items) {
        try (Connection conn = dataSource.getConnection()) {
            return queryExecutor.executeSqlUpdateAffectedRowsCount(conn, sql, items);
        } catch (SQLException ssql) {
            throw new ApplicationDatabaseException(ssql);
        }
        //return 0;
    }

    public List<I> updateAndReturnAffectedIds(String sql, Object ... parameters) {
        List<I> keyList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
            ResultSet result = queryExecutor.executeSqlUpdateAffectedIds(conn, sql, parameters)) {
                while (result.next()) {
                    keyList.add((I)result.getObject(1));
                }
            DbUtils.closeQuietly(result.getStatement());
        } catch (SQLException ssql) {
            throw new ApplicationDatabaseException(ssql);
        }
        return keyList;
    }

    public List<I> updateAndReturnAffectedIds(String sql, List<Object[]> items) {
        List<I> keyList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
            ResultSet result = queryExecutor.executeSqlUpdateAffectedIds(conn, sql, items)) {
            while (result.next()) {
                keyList.add((I)result.getObject(1));
            }
            DbUtils.closeQuietly(result.getStatement());
        } catch (SQLException ssql) {
            throw new ApplicationDatabaseException(ssql);
        }
        return keyList;
    }


    private static List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        while (rs.next()) {
            Map<String, Object> row = new HashMap<String, Object>(columns);
            for (int i = 1; i <= columns; ++i) {
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
        if (rs.next()) {
            row = new HashMap<String, Object>(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
        }
        return row;
    }
}
