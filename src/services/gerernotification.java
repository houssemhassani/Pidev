/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import Utils.DBConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author hamza
 */
public class gerernotification {
    
     private static Connection cnx=DBConnexion.connecterDB();
    public gerernotification() {
    }
    
   public static void ajouternotification(int num_notification,String type_notification,String email_notificaction)
    {
        PreparedStatement select;
         ResultSet resultat;
         PreparedStatement insert;
         try {
            select= cnx.prepareStatement("Select * from notification where num_notification="+num_notification+"");
            resultat=select.executeQuery();
            if(!resultat.isBeforeFirst())
            {
                insert=cnx.prepareStatement("INSERT INTO notification (num_notification,type_notification,email_notification) values (?,?,?)");
                insert.setInt(1, num_notification);
                insert.setString(2, type_notification);
                insert.setString(3, email_notificaction);
               
                
                
                insert.executeUpdate();
                System.out.println("notification ajoutee");
            }
            else
                 System.err.println("existe deja");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }
   
     public static void modifiernotification(int num_notification,String type_notification)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement update;
        try {
             select= cnx.prepareStatement("Select * from notification where num_notification="+num_notification+"");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("Update notification set type_notification=? where num_notification=?" );
                update.setInt(2,num_notification);
                update.setString(1,type_notification);
           
               
                
                update.executeUpdate();
                System.out.println("modification terminée avec succes");
            }
            else
                System.err.println("erreur ");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
   
   
   
   
   
     public static void supprimerdemande(int num_notification)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement delete;
         try {
            select= cnx.prepareStatement("Select * from notification where num_notification="+num_notification+"");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                delete=cnx.prepareStatement("Delete from notification where num_notification='"+num_notification+"'");
                delete.executeUpdate();
                System.out.println("notification supprimee");
            }
            else
                 System.err.println("failed");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }
      
  
   
}
