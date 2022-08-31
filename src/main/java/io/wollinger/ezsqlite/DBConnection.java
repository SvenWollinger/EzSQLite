package io.wollinger.ezsqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class DBConnection {
    private static final HashMap<String, DBConnection> connections = new HashMap<>();
    private Connection connection;

    public static DBConnection getConnection(String file) {
        try {
            Class.forName("org.sqlite.JDBC");
            if(connections.containsKey(file)) {
                DBConnection conn = connections.get(file);
                if(!conn.connection.isClosed())
                    return conn;
            }

            Connection conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", file));
            DBConnection dbConn = new DBConnection();
            dbConn.connection = conn;
            connections.put(file, dbConn);
            return dbConn;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteEntry(String table, DBCondition<?>... conditions) {
        StringBuilder conditionStr = new StringBuilder();
        for(DBCondition<?> cond : conditions) {
            conditionStr.append(String.format("%s%s?", cond.getKey(), cond.typeAsString()));

            if(cond != conditions[conditions.length - 1])
                conditionStr.append(" AND ");
        }

        String query = String.format("delete from %s where %s", table, conditionStr);

        //DATABASE START
        try {
            PreparedStatement sqlQuery = connection.prepareStatement(query);

            int index = 1;
            for(DBCondition<?> condition : conditions) {
                sqlQuery.setObject(index, condition.getValue());
                index++;
            }

            sqlQuery.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
