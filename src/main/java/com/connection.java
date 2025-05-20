package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class connection { 

	

	public static Connection getConnection() {
        Connection con = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2","root","Sudhar@2502");
        } 
        catch (Exception e) {
            System.out.print(e);
            
        }
        return con;
        }

}