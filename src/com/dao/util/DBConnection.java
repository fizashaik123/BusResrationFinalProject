package com.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection Connection(){
        String url = "jdbc:mysql://localhost:3306/dao";
        String username = "root";
        String password = "root";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class not found exception");
        }catch (SQLException e){
            System.out.println("sql not found ");
        }
        return null;
    }
}


