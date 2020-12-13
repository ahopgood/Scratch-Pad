package com.alexander.scratchpad.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtils {
    public static void printColumns(ResultSet rs) throws SQLException {
        System.out.println(rs.toString());
        int count = rs.getMetaData().getColumnCount();
        for (int i = 1; i <= count; i++) {
            System.out.println("Column " + i + " "
                + rs.getMetaData().getColumnLabel(i) + ", value: " + rs.getString(i)
            );
        }
    }
}
