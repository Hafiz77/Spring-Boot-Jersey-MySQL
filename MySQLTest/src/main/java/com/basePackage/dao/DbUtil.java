package com.basePackage.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Hafiz on 4/20/2016.
 */
public class DbUtil {

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }
    }
}
