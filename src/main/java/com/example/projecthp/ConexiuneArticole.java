package com.example.projecthp;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexiuneArticole {
    public Connection databaseLink;

    public  Connection getDBConnection(){

        String databaseName = "users";
        String databaseUser = "root";
        String databasePassword = "rootpassword";
        String url = "jdbc:mysql://localhost/"  + databaseName;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}