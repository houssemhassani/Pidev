/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Citoyen;
import Utils.DBConnexion;
import Utils.JavaMailUtil;
import impl.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class CitoyenLoginService {
    private static Connection cnx=DBConnexion.getConnexion();
    
    
    public CitoyenLoginService(){}
    public static void inscrire(String nom,String prenom,String email,String cin,long num_tel,String ville,String photo,String mot_de_passe)
    {
         PreparedStatement select;
         ResultSet resultat;
         PreparedStatement insert;
        try {
            Citoyen citoyen=new Citoyen(nom, prenom, email, cin, num_tel, ville,photo ,mot_de_passe);
            select=cnx.prepareStatement("select * from citoyen where cin=?");
            select.setString(1, cin);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                System.err.println("Utilisateur existe");
            }
            else
            {
                insert=cnx.prepareCall("INSERT INTO citoyen(nom,prenom,email,cin,num_tel,ville,mot_de_passe) VALUES(?,?,?,?,?,?,?)");
                insert.setString(1,nom);
                insert.setString(2,prenom);
                insert.setString(3, email);
                insert.setString(4, cin);
                insert.setLong(5, num_tel);
                insert.setString(6, ville);
                
                insert.setString(7,BCrypt.hashpw(mot_de_passe,BCrypt.gensalt()));
                insert.executeLargeUpdate();
                
                    JavaMailUtil.sendEmail(email, "Bienvenue sur E-Citoyen \n"+" Attendez la confimation de l'Admin pour accéder à notre application \n Vous serez notifiés via une mail de confirmation \n Merci !! ");
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        catch (Exception exx) {
            System.err.println(exx.getMessage());
            
        }
        
        System.out.println("citoyen ajoutee");
    }
    public static void modifierMotDePasse(String cin,String ancien_mot_de_passe,String nouveau_mot_de_passe)
    {
            PreparedStatement select;
            ResultSet resultat;
            PreparedStatement update;
            Citoyen cit=new Citoyen();
        try {
            select=cnx.prepareStatement("Select * from citoyen where cin='"+cin+"'");
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
                update=cnx.prepareStatement("Update citoyen set mot_de_passe='"+BCrypt.hashpw(nouveau_mot_de_passe,BCrypt.gensalt())+"' Where cin='"+cin+"'");
                update.executeLargeUpdate();
                System.out.println("mot de passe modifiee");
            }
            else 
                System.err.println("lancien mot de passe est incorrect");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
         
    }
     public static void modifierPhoto(String cin,String mot_de_passe,String photo)
    {
            PreparedStatement select;
            ResultSet resultat;
            PreparedStatement update;
            Citoyen cit=new Citoyen();
        try {
            
            select=cnx.prepareStatement("Select * from citoyen where cin='"+cin+"'");
            resultat=select.executeQuery();
            while (resultat.next()) {
            cit.setNom(resultat.getString("nom"));
            cit.setPrenom(resultat.getString("prenom"));
            cit.setEmail(resultat.getString("email"));
            cit.setCin(resultat.getString("cin"));
            cit.setMot_de_passe(resultat.getString("mot_de_passe"));
            }
            if(BCrypt.checkpw(mot_de_passe,cit.getMot_de_passe()))
            {
                update=cnx.prepareStatement("Update citoyen set photo='"+photo+"' Where cin='"+cin+"'");
                update.executeLargeUpdate();
                System.out.println("photo modifiee");
            }
            else
            {
                System.err.println(" mot de passe est incorrect");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
         
    }
     public static void seConnecter(String cin,String mot_de_passe)
     {
         PreparedStatement select;
         ResultSet resultat;
         Citoyen cit=new Citoyen();
        try {
            select=cnx.prepareStatement("Select nom,prenom,email,cin,mot_de_passe from citoyen where cin='"+cin+"'");
            resultat=select.executeQuery();
            while (resultat.next()) {
            cit.setNom(resultat.getString("nom"));
            cit.setPrenom(resultat.getString("prenom"));
            cit.setEmail(resultat.getString("email"));
            cit.setCin(resultat.getString("cin"));
            cit.setMot_de_passe(resultat.getString("mot_de_passe"));
            }
            if((BCrypt.checkpw(mot_de_passe,cit.getMot_de_passe()))&&(cit.isEmail_confirmed()==true))
                System.out.println("citoyen connectee");
            else 
                System.err.println("mdp incorrecte");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
    
}
