/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Utils.DBConnexion;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import services.gererdemande;
import  services.gerernotification;

/**
 *
 * @author hamza
 */
public class Test {
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DBConnexion.connecterDB();
        //Date aujourdhui = SystemClockFactory.getDatetime();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
Date now = new Date();
        DateFormat dateFormatYMD = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        String vDateYMD = dateFormatYMD.format(now);
     //gererdemande.ajouterdemande(6, "bonjour",  vDateYMD, 6, 46);
     //gererdemande.modifierdemande(6, "bonsoir");
      // gererdemande.supprimerdemande(6);
      
      //gerernotification.ajouternotification(15, "facture", "hamzaabda09@gmail.com");
      //gerernotification.modifiernotification(15, "paiment");
      gerernotification.supprimerdemande(15);
    }
    
    
    
}
