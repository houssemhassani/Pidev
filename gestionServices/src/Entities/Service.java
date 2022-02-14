/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author rachdi
 */
public class Service {
   
    
    
    private int id;
    private String nomService ;

    
    public Service() {}
    
    
    public Service( String nomService) {
        
        this.nomService = nomService;
    }

    public int getId() {
        return id;
    }
    public void setId(int id)
	{ this.id=id;} 

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", nomService=" + nomService + '}';
    }
    
    
    
    
    

    
    
    
    
    
}

