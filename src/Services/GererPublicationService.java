/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Publication;
import Utils.DBConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ghassen-pc
 */
public class GererPublicationService {
    private static Connection cnx=DBConnexion.getConnexion();

    public GererPublicationService() {
    }
    
    public static void ajouterPublication(String status,String photo,String utilisateur_nom,String utilisateur_prenom)
    {
        
         ResultSet resultat;
         PreparedStatement insert;
         try {
            insert=cnx.prepareStatement("insert into publication (status,photo,utilisateur_nom,utilisateur_prenom) values(?,?,?,?)");
            insert.setString(1, status);
            insert.setString(2, photo);
            insert.setString(3, utilisateur_nom);
            insert.setString(4, utilisateur_prenom);
            insert.executeUpdate();
             System.out.println("publication ajoutee");
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }
        
    }
    public static void modifierStatusPublication(int id,String status)
    {
        ResultSet resultat;
         PreparedStatement update;
         PreparedStatement select;
         try {
            select=cnx.prepareStatement("select * from publication where id=?");
            select.setInt(1, id);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("update publication set status=? where id=?");
                update.setString(1, status);
                update.setInt(2, id);
                update.executeUpdate();
                System.out.println("status modifiee");
            }
            else
                 System.err.println("publication introuvable");
        } catch (SQLException e) {
             System.err.println(e.getMessage());
        }
    }
    
    
    
    public static void modifierPhotoPublication(int id,String photo)
    {
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;
        try {
            select=cnx.prepareStatement("select * from publication where id ="+id);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("update publication set photo='"+photo+"'");
                update.executeUpdate();
                System.out.println("photo modifiee");
            }
            else
                System.err.println("publication introuvable");
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void supprimerPublication(int id)
    {
        PreparedStatement  delete;
        PreparedStatement select;
        ResultSet resultat;
        try {
            select=cnx.prepareStatement("select * from publication where id="+id);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                delete=cnx.prepareStatement("delete from publication where id="+id);
                delete.executeUpdate();
                System.out.println("publication supprimeeeee");
            }
            else
                System.err.println("publication non trouvable");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public static ArrayList<Publication>  getPublicationsConfirmee()
    {
        PreparedStatement select;
        ResultSet resultat;
        ArrayList<Publication> pubs=new ArrayList<Publication>();
        try {
            select=cnx.prepareStatement("select * from publication where confirm_publication=?");
            select.setBoolean(1, true);
            resultat=select.executeQuery();
            while(resultat.next())
            {
                Publication pub=new Publication();
                pub.setId(resultat.getInt("id"));
                pub.setStatus(resultat.getString("status"));
                pub.setPhoto(resultat.getString("photo"));
                pub.setUtilisateur_nom("utilisateur_nom");
                pub.setUtilisateur_prenom("utilisateur_prenom");
                pub.setConfirm_publication(true);
                pubs.add(pub);
                
            }
           
            
        } catch (SQLException e) {
            return null;
        }
         return pubs;
    }
    public static ArrayList<Publication> getPublicationsNonConfirmee()
    {
        PreparedStatement select;
        ResultSet resultat;
        ArrayList<Publication> pubs=new ArrayList<Publication>();
        try {
            select=cnx.prepareStatement("select * from publication where confirm_publication=?");
            select.setBoolean(1, false);
            resultat=select.executeQuery();
            while(resultat.next())
            {
                Publication pub=new Publication();
                pub.setId(resultat.getInt("id"));
                pub.setStatus(resultat.getString("status"));
                pub.setPhoto(resultat.getString("photo"));
                pub.setUtilisateur_nom("utilisateur_nom");
                pub.setUtilisateur_prenom("utilisateur_prenom");
                pub.setConfirm_publication(false);
                pubs.add(pub);
                
            }
           
            
        } catch (SQLException e) {
            return null;
        }
         return pubs;
        
    }
}
