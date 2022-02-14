/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Utils.DBConnexion;

import services.GestionReclamation;







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
      GestionReclamation.ajouterReclamation(0, "telecom", "bizerte", "facture");
        GestionReclamation.ajouterReclamation(0, "Soned", "Sfax", "facture");
        GestionReclamation.modefierReclamationVille(5, "nabeul");
        GestionReclamation.supprimerReclamation(7);
        GestionReclamation.modefierReclamationType(6, "devis");
        GestionReclamation.modefierReclamationDescription(5, "STEG ******");

    }
    
    
    
}
