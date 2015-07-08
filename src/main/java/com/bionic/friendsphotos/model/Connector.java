package com.bionic.friendsphotos.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by c265 on 07.07.2015.
 */
public class Connector {

    private static String HOST = "jdbc:mysql://sql4.freemysqlhosting.net:3306/sql482691";
    private static String USER_NAME = "sql482691";
    private static  String USER_PASSWORD = "rU3*cI8*";

    private static Connection connection;

    public static final Connection getConnection() {
        try {
             Class.forName("com.mysql.jdbc.Driver");
             connection = DriverManager.getConnection(HOST, USER_NAME, USER_PASSWORD);
        } catch (ClassNotFoundException ex) { /*NOP*/ }
        catch (SQLException ex) { /*NOP*/}
        return connection;
    }
}
