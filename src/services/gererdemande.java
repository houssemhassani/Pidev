/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author hamza
 */

import Entities.demande;
import Utils.DBConnexion;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class gererdemande implements gestiondemande {
    
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
    private static Connection cnx=DBConnexion.connecterDB();

    
    public gererdemande() {
    }
    
   public static void ajouterdemande(int num_demande,String type_demande,String date_demande,int id_service,int id_citoyen)
    {
        PreparedStatement select;
         ResultSet resultat;
         PreparedStatement insert;
         try {
            select= cnx.prepareStatement("Select * from demande where num_demande="+num_demande+"");
            resultat=select.executeQuery();
            if(!resultat.isBeforeFirst())
            {
                insert=cnx.prepareStatement("INSERT INTO demande (num_demande,date_demande,type_demande,id_service,id_cityon) values (?,?,?,?,?)");
                insert.setInt(1, num_demande);
               java.sql.Date date = new java.sql.Date(0000-00-00);
                insert.setDate(2,new java.sql.Date(22, 02, 12));
                insert.setString(3, type_demande);
                insert.setInt(4, id_service);
                insert.setInt(5, id_citoyen);
                
                
                insert.executeUpdate();
                System.out.println("demande ajoutee");
            }
            else
                 System.err.println("demande existe");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }
   
    
   public static void modifierdemande(int num_demande,String type_demande)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement update;
        try {
             select= cnx.prepareStatement("Select * from demande where num_demande="+num_demande+"");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("Update demande set type_demande=? where num_demande=?" );
                update.setInt(2,num_demande);
                update.setString(1,type_demande);
           
               ;
                
                update.executeUpdate();
                System.out.println("modification terminée avec succes");
            }
            else
                System.err.println("erreur ");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
   
   
   
     public static void supprimerdemande(int num_demande)
    {
        PreparedStatement select;
        ResultSet resultat;
        PreparedStatement delete;
         try {
            select= cnx.prepareStatement("Select * from demande where num_demande="+num_demande+"");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                delete=cnx.prepareStatement("Delete from demande where num_demande='"+num_demande+"'");
                delete.executeUpdate();
                System.out.println("demande supprimee");
            }
            else
                 System.err.println("failed");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        
    }
   
   
      
    public List<demande> afficher() {
        List<demande> personnes = new ArrayList<>();
        String req = "SELECT * FROM `demande`";
        
        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();
            Statement 
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                demande p = new demande();
                p.setNum_demande(rs.getInt("num_demande"));
                p.setType_demande(rs.getString(2));
                //p.setDate_demande(rs.getDate(3));
                  p.setId_citoyen(rs.getString(4));
                    p.setId_service(rs.getString(5));
                personnes.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(gererdemande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return personnes;
    }

    @Override
    public void ajouterdemande(demande p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierdemande(demande p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerdemande(demande p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    }
     
     
     
     
     
     
     
     
     
     
     
   
  

   
   
   
   
   


 
    