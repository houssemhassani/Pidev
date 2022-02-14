/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rachdi
 */
public class DBConnection {
    
    public static Connection getConnection(){
        try {
            String url ="jdbc:mysql://localhost:3306/servicedb";
            String user = "root";
            String pwd = "";
            
            Connection cnx = DriverManager.getConnection(url, user, pwd);
            
            System.out.println("connecté");
            return cnx;
            
        } catch (SQLException e) {
            return null;
            
        }
    }
    
    
}
