package io.wollinger.ezsqlite;

import org.sqlite.core.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class DBManager {
    private static final HashMap<String, Connection> connections = new HashMap<>();

    public static void init() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(String file) {
        if(connections.containsKey(file)) {
            try {
                connections.get(file).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection(String file) {
        try {
            if(connections.containsKey(file)) {
                Connection conn = connections.get(file);
                if(!conn.isClosed())
                    return conn;
            }

            Connection conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", file));
            connections.put(file, conn);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
