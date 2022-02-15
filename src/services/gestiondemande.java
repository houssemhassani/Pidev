/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.demande;
import java.util.List;

/**
 *
 * @author hamza
 */
public interface gestiondemande {
    void ajouterdemande(demande p);
    void modifierdemande(demande p);
    void supprimerdemande(demande p);
    List<demande> afficher();
}
