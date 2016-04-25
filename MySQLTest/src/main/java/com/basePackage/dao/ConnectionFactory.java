package com.basePackage.dao;

import com.basePackage.utility.AppConstant;
import com.basePackage.utility.ReadProperties;

import java.sql.*;

/**
 * Created by Hafiz on 4/20/2016.
 */

public class ConnectionFactory {
    public static ConnectionFactory db;
    public static Connection conn;
    private static Statement statement;


    //static reference to itself
    private static ConnectionFactory instance = new ConnectionFactory();

    public static final String URL =  ReadProperties.demoProperties().getProperty(AppConstant.DB_PROPERTIES_DB_URI);
    public static final String USER =ReadProperties.demoProperties().getProperty(AppConstant.DB_PROPERTIES_DB_USER);
    public static final String PASSWORD = ReadProperties.demoProperties().getProperty(AppConstant.DB_PROPERTIES_DB_PASSWORD);
    public static final String DRIVER_CLASS = AppConstant.DB_PROPERTIES_DB_DRIVER;

    //private constructor
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }
    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized ConnectionFactory getDbCon() {
        if ( db == null ) {
            db = new ConnectionFactory();
        }
        return db;

    }
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException{
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }


    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public static int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;

    }


}
