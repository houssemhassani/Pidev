
import Entities.Service;
import Service.GestionServices;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rachdi
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {

        Service s1 = new Service("municipalité");
        
        
        GestionServices.supprimerService("Service financier");
    }
    
}
