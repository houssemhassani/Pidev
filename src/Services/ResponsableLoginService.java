/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Responsable;
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
public class ResponsableLoginService {
    private static Connection cnx=DBConnexion.getConnexion();
    
    public ResponsableLoginService() {
    }
    
    public static void modifierMotDePasse(String cin,String ancien_mot_de_passe,String nouveau_mot_de_passe)
    {
            PreparedStatement select;
            ResultSet resultat;
            PreparedStatement update;
            Responsable cit=new Responsable();
        try {
            select=cnx.prepareStatement("Select * from responsable where cin='"+cin+"'");
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
                update=cnx.prepareStatement("Update responsable set mot_de_passe='"+BCrypt.hashpw(nouveau_mot_de_passe,BCrypt.gensalt())+"' Where cin='"+cin+"'");
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
            Responsable cit=new Responsable();
        try {
            
            select=cnx.prepareStatement("Select * from responsable where cin='"+cin+"'");
            resultat=select.executeQuery();
            if (resultat.isBeforeFirst())
            {
                while (resultat.next()) {
                    cit.setNom(resultat.getString("nom"));
                    cit.setPrenom(resultat.getString("prenom"));
                    cit.setEmail(resultat.getString("email"));
                    cit.setCin(resultat.getString("cin"));
                    cit.setMot_de_passe(resultat.getString("mot_de_passe"));
            }
                if(BCrypt.checkpw(mot_de_passe,cit.getMot_de_passe()))
                {
                    update=cnx.prepareStatement("Update responsable set email='"+email+"' Where cin='"+cin+"'");
                    update.executeLargeUpdate();
                    System.out.println("email modifiee");
                }
                else
                {
                    System.err.println(" mot de passe est incorrect");
                }
            }
            else
                System.out.println("Compte introuvable");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
         
    }
     public static void seConnecter(String cin,String mot_de_passe)
     {
         PreparedStatement select;
         ResultSet resultat;
         Responsable cit=new Responsable();
        try {
            select=cnx.prepareStatement("Select nom,prenom,email,cin,mot_de_passe from responsable where cin='"+cin+"'");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                while (resultat.next()) {
                    cit.setNom(resultat.getString("nom"));
                    cit.setPrenom(resultat.getString("prenom"));
                    cit.setEmail(resultat.getString("email"));
                    cit.setCin(resultat.getString("cin"));
                    cit.setMot_de_passe(resultat.getString("mot_de_passe"));
                }
                if(BCrypt.checkpw(mot_de_passe,cit.getMot_de_passe()))
                    System.out.println("responsable connectee");
                else 
                    System.err.println("mdp incorrecte");
            }
            else
                System.err.println("Compte introuvable");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     } 
}
