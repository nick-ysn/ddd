package me.liberty.ddd.transction.script.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 *
 * @author lanjue
 * @version $Id: me.bliss.ddd.transction.script.utils, v 0.1 2/26/16
 *          Exp $
 */
public class DBUtils {

    private final static String connectStr = "jdbc:mysql://127.0.0.1:3306/test";

    private final static String user = "root";

    private final static String password = "yuanshouna123?";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectStr, user, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("DB Driver init fail");
        } catch (SQLException e) {
            throw new RuntimeException("DB init fail!");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
