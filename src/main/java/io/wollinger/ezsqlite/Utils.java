package io.wollinger.ezsqlite;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Utils {
    public static ArrayList<HashMap<String, Object>> resultSetToArrayList(ResultSet rs) {
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        try {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            while (rs.next()){
                HashMap<String, Object> row = new HashMap<>();
                for(int i = 1; i <= columns; ++i){
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(row);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return list;
    }

}
