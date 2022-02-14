/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Admin
 */

/**
 *
 * @author hamza
 */

import Utils.DBConnexion;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;



public class GestionReclamation{
    private static Connection cnx=DBConnexion.connecterDB();

    
    public GestionReclamation() {
    }
    
   public static void ajouterReclamation(int id, String description_reclamation ,String ville_reclamation,String type_reclamation)
    {
        PreparedStatement select;
         ResultSet resultat;
         PreparedStatement insert;
         try {
            select= cnx.prepareStatement("Select * from réclamation where id_reclamation="+id+"");
            resultat=select.executeQuery();
            if(!resultat.isBeforeFirst())
            {
                insert=cnx.prepareStatement("INSERT INTO réclamation (description_reclamtion,ville_reclamation,type_reclamation) values (?,?,?)");
                insert.setString(1, description_reclamation);
                insert.setString(2, ville_reclamation);
                insert.setString(3, type_reclamation);
        
                
                
                insert.executeUpdate();
                System.out.println("reclamation ajoutee");
            }
            else
                 System.err.println(" existe");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
         
         
         
             
         
    }
   public static void modefierReclamationVille(int id ,String ville_reclamation)
    {
        PreparedStatement select;
         ResultSet resultat;
         PreparedStatement update;
         try {
            select= cnx.prepareStatement("Select * from réclamation where id_reclamation="+id+"");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("UPDATE  réclamation  set ville_reclamation=? where id_reclamation=?");
                update.setString(1,ville_reclamation);
                update.setInt(2,id);
                update.executeUpdate();
                System.out.println("ville modifiee");
        
                
                
                update.executeUpdate();
                System.out.println("modification terminee");
            }
            else
                 System.err.println(" existe");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }

    public static void modefierReclamationDescription(int id ,String description_reclamation)
    {
        PreparedStatement select;
         ResultSet resultat;
         PreparedStatement update;
         try {
            select= cnx.prepareStatement("Select * from réclamation where id_reclamation="+id+"");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("UPDATE  réclamation  set description_reclamtion=? where id_reclamation=?");
                update.setString(1,description_reclamation);
                update.setInt(2,id);
                update.executeUpdate();
                System.out.println("description modifiee");
        
                
                
                update.executeUpdate();
                System.out.println("modification terminee");
            }
            else
                 System.err.println(" existe");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }
   public static void modefierReclamationType(int id ,String type_reclamation)
    {
        PreparedStatement select;
         ResultSet resultat;
         PreparedStatement update;
         try {
            select= cnx.prepareStatement("Select * from réclamation where id_reclamation="+id+"");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("UPDATE  réclamation  set type_reclamation=? where id_reclamation=?");
                update.setString(1,type_reclamation);
                update.setInt(2,id);
                update.executeUpdate();
                System.out.println("type modifiee");
        
                
                
                update.executeUpdate();
                System.out.println("modification terminee");
            }
            else
                 System.err.println(" existe");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }
   
public static void supprimerReclamation(int id)
{
    PreparedStatement select ;
    ResultSet resultat;
    PreparedStatement delete;
    try{
            select= cnx.prepareStatement("Select * from réclamation where id_reclamation="+id+"");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                delete=cnx.prepareStatement("Delete from réclamation where id_reclamation='"+id+"'");
                delete.executeUpdate();
                System.out.println("Réclamation supprimee");
            }
            else
                 System.err.println("failed");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }



}

         
         
             
         
    }
   
    
   
   
   
   
   
   
   
   
   
   

