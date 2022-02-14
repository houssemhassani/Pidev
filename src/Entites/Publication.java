/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author Ghassen-pc
 */
public class Publication {
    private int id;
    private String status;
    private String photo;
    private String utilisateur_nom;
    private String utilisateur_prenom;
    private boolean confirm_publication; 

    public Publication() {
    }

    public Publication( String status, String photo, String utilisateur_nom, String utilisateur_prenom) {
        this.status = status;
        this.photo = photo;
        this.utilisateur_prenom = utilisateur_prenom;
        this.utilisateur_nom = utilisateur_nom;
        this.confirm_publication = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUtilisateur_prenom() {
        return utilisateur_prenom;
    }

    public void setUtilisateur_prenom(String utilisateur_prenom) {
        this.utilisateur_prenom = utilisateur_prenom;
    }

   
    public String getUtilisateur_nom() {
        return utilisateur_nom;
    }

    public void setUtilisateur_nom(String utilisateur_nom) {
        this.utilisateur_nom = utilisateur_nom;
    }

    public boolean isConfirm_publication() {
        return confirm_publication;
    }

    public void setConfirm_publication(boolean confirm_publication) {
        this.confirm_publication = confirm_publication;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", status=" + status + ", photo=" + photo + ", utilisateur_nom=" + utilisateur_nom + ", utilisateur_prenom=" + utilisateur_prenom + ", confirm_publication=" + confirm_publication + '}';
    }

    
    
    
    
}
