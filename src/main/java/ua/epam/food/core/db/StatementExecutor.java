package ua.epam.food.core.db;

import java.sql.*;
import java.util.Arrays;

public class StatementExecutor {

    public ResultSet executeStatement(PreparedStatement statement ) throws SQLException {
        return statement.executeQuery();
    }

    public int executeUpdateStatementAffectedRowsCount(PreparedStatement statement ) throws SQLException{
        return statement.executeUpdate();
    }

    public ResultSet executeUpdateStatementAffectedIds(PreparedStatement statement ) throws SQLException{
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Update failed, no rows affected.");
        }
        return statement.getGeneratedKeys();
    }

    public ResultSet executeBatchStatement(PreparedStatement statement ) throws SQLException {
        return statement.executeQuery();
    }

    public int executeUpdateBatchStatementAffectedRowsCount(PreparedStatement statement ) throws SQLException{
        int[] affectedRows = statement.executeBatch();
        return sum(affectedRows);
    }

    public ResultSet executeUpdateBatchStatementAffectedIds(PreparedStatement statement ) throws SQLException{
        int[] affectedRows = statement.executeBatch();
        if (sum(affectedRows) == 0) {
            throw new SQLException("Update failed, no rows affected.");
        }
        return statement.getGeneratedKeys();
    }

    private int sum(int [] array){
        return Arrays.stream(array).sum();
    }
}
