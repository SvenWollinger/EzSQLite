package io.wollinger.ezsqlite;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class DBResultSet {
    private final ArrayList<HashMap<String, Object>> data;

    public DBResultSet(ResultSet rs) {
        data = Utils.resultSetToArrayList(rs);
    }

    public int length () {
        return data.size();
    }

    public String getString(int row, String key) {
        return (String) getObject(row, key);
    }

    public int getInt(int row, String key) {
        return (int) getObject(row, key);
    }

    public double getDouble(int row, String key) {
        return (double) getObject(row, key);
    }

    public float getFloat(int row, String key) {
        return (float) getObject(row, key);
    }

    public Object getObject(int row, String key) {
        return data.get(row).get(key);
    }

}