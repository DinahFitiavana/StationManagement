package com.example.station.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class ConnectToDatabase {
    public static ConnectToDatabase instance;
    public static String username = System.getenv("DB_USERNAME");
    public static String password = System.getenv("DB_PASSWORD");
    public static String url = System.getenv("DB_URL");

    public static ConnectToDatabase getInstance(){
        if (instance == null){
            instance = new ConnectToDatabase();
        }
        return instance;
    }

    public Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            if (connection != null){
                System.out.println("Connected to the database successfully!");
            }
            return connection;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
