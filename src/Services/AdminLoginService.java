/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Admin;
import Entities.Citoyen;
import Utils.DBConnexion;
import impl.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class AdminLoginService {
    private static Connection cnx= DBConnexion.getConnexion();

    public AdminLoginService() {
    }
    
    public static void modifierMotDePasse(String cin,String ancien_mot_de_passe,String nouveau_mot_de_passe)
    {
            PreparedStatement select;
            ResultSet resultat;
            PreparedStatement update;
            Citoyen cit=new Citoyen();
        try {
            select=cnx.prepareStatement("Select * from admin where cin='"+cin+"'");
            resultat=select.executeQuery();
            while (resultat.next()) {
            cit.setNom(resultat.getString("nom"));
            cit.setPrenom(resultat.getString("prenom"));
            cit.setEmail(resultat.getString("email"));
            cit.setCin(resultat.getString("cin"));
            cit.setMot_de_passe(resultat.getString("mot_de_passe"));
            }
            if(BCrypt.checkpw(ancien_mot_de_passe,cit.getMot_de_passe()))
            {
                update=cnx.prepareStatement("Update admin set mot_de_passe='"+BCrypt.hashpw(nouveau_mot_de_passe,BCrypt.gensalt())+"' Where cin='"+cin+"'");
                update.executeLargeUpdate();
                System.out.println("mot de passe modifiee");
            }
            else 
                System.err.println("lancien mot de passe est incorrect");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
         
    }
     public static void modifierEmail(String cin,String mot_de_passe,String email)
    {
            PreparedStatement select;
            ResultSet resultat;
            PreparedStatement update;
            Admin admin=new Admin();
        try {
            
            select=cnx.prepareStatement("Select * from admin where cin='"+cin+"'");
            resultat=select.executeQuery();
            while (resultat.next()) {
            admin.setNom(resultat.getString("nom"));
            admin.setPrenom(resultat.getString("prenom"));
            admin.setEmail(resultat.getString("email"));
            admin.setCin(resultat.getString("cin"));
            admin.setMot_de_passe(resultat.getString("mot_de_passe"));
            }
            if(BCrypt.checkpw(mot_de_passe,admin.getMot_de_passe()))
            {
                update=cnx.prepareStatement("Update admin set email='"+email+"' Where cin='"+cin+"'");
                update.executeLargeUpdate();
                System.out.println("email modifiee");
            }
            else
            {
                System.err.println(" mot de passe est incorrect");
            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        catch (NullPointerException e) {
            System.err.println(e.getMessage());
            
        } 
        
         
    }
     public static void seConnecter(String cin,String mot_de_passe)
     {
         PreparedStatement select;
         ResultSet resultat;
         Admin cit=new Admin();
        try {
            select=cnx.prepareStatement("Select nom,prenom,email,cin,mot_de_passe from admin where cin='"+cin+"'");
            resultat=select.executeQuery();
            while (resultat.next()) {
            cit.setNom(resultat.getString("nom"));
            cit.setPrenom(resultat.getString("prenom"));
            cit.setEmail(resultat.getString("email"));
            cit.setCin(resultat.getString("cin"));
            cit.setMot_de_passe(resultat.getString("mot_de_passe"));
            }
            if(BCrypt.checkpw(mot_de_passe,cit.getMot_de_passe()))
                System.out.println("admin connectee");
            else 
                System.err.println("mdp incorrecte");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
    
}
