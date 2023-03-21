package org.example.JDBC.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static String Taipowerurl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimeZone=UTC&characterEncoding=UTF-8";
    private static String user = "Jean";
    private static String password = "0124";
    private static Connection conn = null;

    public ConnectionUtil() {
    }

    public static Connection getConnectionToTaipower() {
        try {
            conn = DriverManager.getConnection(Taipowerurl, user, password);
        } catch (SQLException var1) {
            var1.printStackTrace();
        }

        return conn;
    }

    public static boolean freetaipower() {
        if (conn == null) {
            return false;
        } else {
            try {
                if (conn.isClosed()) {
                    return true;
                } else {
                    conn.close();
                    return true;
                }
            } catch (SQLException var1) {
                var1.printStackTrace();
                return false;
            }
        }
    }
}