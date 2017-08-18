package com.cine.rest_spring.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conectar 
{
    static Connection conn;
    
    public static Connection con() throws ClassNotFoundException
    {
        try
       {
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=cinepolis","sa","progra");
           System.out.println("Listo");
           return conn;
       }
       catch(SQLException ex)
       {
           return conn = null;
       }
    }
}
