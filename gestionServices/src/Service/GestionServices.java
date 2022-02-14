/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Service;

import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rachdi
 */
public class GestionServices  {
    
    private static Connection cnx = DBConnection.getConnection();
    
    
    
   
    public GestionServices() {
    }
    
    /**
     *
     * @param s
     * @throws SQLException
     */
    public static void ajouterService(Service s) throws SQLException{
        PreparedStatement select;
        PreparedStatement insert;
        
        ResultSet resultat;
        
        if (s==null){
            System.err.println("SERVICE VIDE");
        }
        else {
            try {
                select=cnx.prepareStatement("Select * from service where nomService='"+s.getNomService()+"'");
                resultat = select.executeQuery();
                
                if (!resultat.isBeforeFirst()) {
                    insert=cnx.prepareStatement("insert into service (nomService) values (?)");
                    insert.setString(1, s.getNomService());
                    insert.executeUpdate();
                    System.out.println("Service ajouté");
                    
                    
                    
                } else {
                    
                    System.err.println("Service existant");
                }
            }
            catch(SQLException ex){
                System.err.println(ex.getMessage());
    
            }
        }
        
    }
    
    public static void modifierService(String nomServiceAncien , String nomServiceNouveau){
       
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;
        try
        {
            select=cnx.prepareStatement("select * from service where nomService='"+ nomServiceAncien+"'");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst()){
                update=cnx.prepareStatement("update service set nomService='"+nomServiceNouveau+"' where nomService='"+nomServiceAncien +"'");
                update.executeUpdate();
                System.out.println("Nom du service modifié avec succes");
            }
            else {
            System.err.println("service non trouvé");
            }
        }     
        catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }
    }
    
    public static void supprimerService (String nomService){
        
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;
        try
        {
            select=cnx.prepareStatement("select * from service where nomService='"+nomService+"'");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("delete from service where nomService='"+nomService+"'");
                update.executeUpdate();
                System.out.println("Service supprimee avec succeess");
            }
            else
                System.err.println("Service non trouvee");
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public static ArrayList<Service> getAllService()
    {
	PreparedStatement select;
	ResultSet resultat;
	ArrayList<Service> services=new ArrayList<Service>();
	try {
		select=cnx.prepareStatement("select * from services");
		resultat=select.executeQuery();
		while(resultat.next())
		{
			Service s1=new Service();
			s1.setId(resultat.getInt("id"));
			s1.setNomService(resultat.getString("nomService"));
			services.add(s1);
		}
	     }
	catch(Exception e)
	     { return null;}
	return services;
     }
         
}