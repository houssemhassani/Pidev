/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.demande;
import Entities.notification;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author hamza
 */
public class DBConnexion {

    
    public static Connection connecterDB(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver ok");
        String url ="jdbc:mysql://localhost:3306/pidev";
        String user ="root";
        String password="";
        Connection cnx = DriverManager.getConnection(url,user,password);
        System.out.println("Connexion bien établie");
        return cnx;
        
        
        
                
        }catch(Exception e){
        e.printStackTrace();
        return null;
        }
    }
}
