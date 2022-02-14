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
public class EmployeeLoginService {
    private static Connection cnx=DBConnexion.getConnexion();

    public EmployeeLoginService() {
    }
    public static void seConnecter(String cin,String mot_de_passe)
     {
         PreparedStatement select;
         ResultSet resultat;
         Responsable cit=new Responsable();
        try {
            select=cnx.prepareStatement("Select * from employee where cin='"+cin+"'");
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
                    System.out.println("employee connectee");
                else 
                    System.err.println("mdp incorrecte");
            }
            else
                System.err.println("cin incorrecte");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
    
    
}
