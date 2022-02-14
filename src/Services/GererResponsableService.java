/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
public class GererResponsableService {
    private static Connection cnx=DBConnexion.getConnexion();

    public GererResponsableService() {
    }
    
    public static void ajouterResponsable(String nom,String prenom,String email,String cin,int service_id,String mot_de_passe)
    {
        PreparedStatement select;
         ResultSet resultat;
         PreparedStatement insert;
         try {
            select= cnx.prepareStatement("Select * from responsable where cin='"+cin+"'");
            resultat=select.executeQuery();
            if(!resultat.isBeforeFirst())
            {
                insert=cnx.prepareStatement("INSERT INTO responsable (nom,prenom,email,cin,service_id,mot_de_passe) values (?,?,?,?,?,?)");
                insert.setString(1, nom);
                insert.setString(2, prenom);
                insert.setString(3, email);
                insert.setString(4, cin);
                insert.setInt(5, service_id);
                
                String mdp;
                mdp=BCrypt.hashpw(mot_de_passe, BCrypt.gensalt());
                insert.setString(6, mdp);
                insert.executeLargeUpdate();
                System.out.println("Responsable ajoutee");
            }
            else
                 System.err.println("Responsable existe");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }
    public static void modifierServiceDeResponsable(String cin,int service_id)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement update;
        try {
            select = cnx.prepareStatement("Select * from responsable where cin='"+cin+"'");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("Update responsable set service_id=? where cin=?");
                update.setInt(1, service_id);
                update.setString(2, cin);
                update.executeLargeUpdate();
                System.out.println("Service modifiee pour ce responsable");
            }
            else
                System.err.println("Responsable n'exitse pas");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void supprimerResponsable(String cin)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement delete;
         try {
            select= cnx.prepareStatement("Select * from responsable where cin='"+cin+"'");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                delete=cnx.prepareStatement("Delete from responsable where cin='"+cin+"'");
                delete.executeLargeUpdate();
                System.out.println("Responsable supprimee");
            }
            else
                 System.err.println("Responsable n'existe pas");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }
    
    
}
