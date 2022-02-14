/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DBConnexion;
import Utils.JavaMailUtil;
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
public class GererCitoyenService {
    private static Connection cnx=DBConnexion.getConnexion();
    public static void confirmerCitoyen(String cin,String email)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement update;
        
        try {
           
                update=cnx.prepareStatement("Update citoyen set email_confirmed=? where cin=?");
                update.setBoolean(1, true);
                update.setString(2, cin);
                JavaMailUtil.sendEmail(email,"Votre compte est confirmée \n Vous pouvez acceder à notre application");
                update.executeLargeUpdate();
                System.out.println("Citoyen confirmee");
                                System.out.println("Citoyen confirmee");

            
          
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(GererCitoyenService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
