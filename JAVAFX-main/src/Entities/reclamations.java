/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class reclamations {
     private int id;
    private String description;
    private String ville;
    private String type;

    public reclamations(String description, String ville, String type) {
        this.description = description;
        this.ville = ville;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public String getVille() {
        return ville;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "reclamations{" + "description=" + description + ", ville=" + ville + ", type=" + type + '}';
    }

    
    
    
    
}
