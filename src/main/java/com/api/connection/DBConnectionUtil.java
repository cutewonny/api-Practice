package com.api.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
    public static Connection getConnection(){
        String URL = null, PASSWORD = "", USERNAME = null;

        try{
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        }
        catch (SQLException e){
            throw new IllegalStateException(e);
        }

    }
}
