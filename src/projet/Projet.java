/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import Entites.Publication;
import Services.GererPublicationService;
import Utils.DBConnexion;
import java.util.ArrayList;

/**
 *
 * @author Ghassen-pc
 */
public class Projet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //DBConnexion.getConnexion();
        //GererPublicationService.ajouterPublication("ghassen", "photo", "utilisateur_nom", "utilisateur_prenom");
        //GererPublicationService.modifierStatusPublication(1, "status");
        //GererPublicationService.modifierPhotoPublication(1, "photooooooooooooooo");
        //GererPublicationService.supprimerPublication(1);
        ArrayList<Publication> pubs;
                //pubs=GererPublicationService.getPublicationsConfirmee();
                pubs=GererPublicationService.getPublicationsNonConfirmee();
        if(pubs.isEmpty())
            System.err.println("tous les publications sont confirmee");
        else
        {
            pubs.forEach((e) -> {
                System.out.println(e.toString()+"\n");
            });
        }
    }
    
}
