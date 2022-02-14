/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Employee;
import Utils.DBConnexion;
import impl.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class GererEmployeeService {
    private static Connection cnx=DBConnexion.getConnexion();

    public GererEmployeeService() {
    }
    public static void ajouterEmployee(Employee e)
    {
        PreparedStatement select;
        PreparedStatement insert;
        ResultSet resultat;
        if(e==null)
            System.err.println("employee vide");
        else 
        {
            try {
            select=cnx.prepareStatement("Select * from employee where cin='"+e.getCin()+"'");
            resultat = select.executeQuery();
            if(!resultat.isBeforeFirst())
            {
                insert=cnx.prepareStatement("insert into employee (nom,prenom,email,cin,service_id,equipe_id,mot_de_passe) values(?,?,?,?,?,?,?)");
                insert.setString(1, e.getNom());
                insert.setString(2, e.getPrenom());
                insert.setString(3, e.getEmail());
                insert.setString(4, e.getCin());
                insert.setInt(5, e.getService_id());
                insert.setInt(6, e.getEquipe_id());
                String mdp=BCrypt.hashpw(e.getMot_de_passe(), BCrypt.gensalt());
                insert.setString(7, mdp);
                insert.executeLargeUpdate();
                System.out.println("employee ajoutee");
            }
            else
            {System.err.println("employee existe");}
        } 
            catch(SQLException ex)
            {
                System.err.println(ex.getMessage());
            }
        }
    }
    public static void modifierEquipeEmployee(String cin, int equipe_id)
    {
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;
        try
        {
            select=cnx.prepareStatement("select * from employee where cin='"+cin+"'");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("update employee set equipe_id="+equipe_id+" where cin='"+cin+"'");
                update.executeLargeUpdate();
                System.out.println("equipe modifiee pour cet employee");
            }
            else
                System.err.println("employee non trouvee");
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public static void modifierServiceEmployee(String cin, int service_id)
    {
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;
        try
        {
            select=cnx.prepareStatement("select * from employee where cin='"+cin+"'");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("update employee set service_id="+service_id+" where cin='"+cin+"'");
                update.executeLargeUpdate();
                System.out.println("equipe modifiee pour cet employee");
            }
            else
                System.err.println("employee non trouvee");
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public static void supprimerEmployee(String cin)
    {
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;
        try
        {
            select=cnx.prepareStatement("select * from employee where cin='"+cin+"'");
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                update=cnx.prepareStatement("delete from employee where cin='"+cin+"'");
                update.executeLargeUpdate();
                System.out.println("employee supprimee avec succeess");
            }
            else
                System.err.println("employee non trouvee");
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    public static ArrayList<Employee> getEmployeesService(int service_id)
    {
        PreparedStatement select;
        ResultSet resultat;
        ArrayList<Employee> emps=new ArrayList<>();
        
        try
        {
            select=cnx.prepareStatement("select * from employee where service_id="+service_id);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                while(resultat.next())
            {
                Employee e=new Employee();
                e.setId(resultat.getInt("id"));
                e.setNom(resultat.getString("nom"));
                e.setPrenom(resultat.getString("prenom"));
                e.setEmail(resultat.getString("email"));
                e.setCin(resultat.getString("cin"));
                e.setMot_de_passe(resultat.getString("mot_de_passe"));
                e.setService_id(resultat.getInt("service_id"));
                e.setEquipe_id(resultat.getInt("equipe_id"));
                emps.add(e);
            }
            }
            else 
                return null;
            
        }
        catch(SQLException ex)
        {
            return null;
        }
        return emps;
    }
    public static ArrayList<Employee> getEmployeesEquipe(int equipe_id)
    {
        PreparedStatement select;
        ResultSet resultat;
        ArrayList<Employee> emps=new ArrayList<>();
        
        try
        {
            select=cnx.prepareStatement("select * from employee where equipe_id="+equipe_id);
            resultat=select.executeQuery();
            if(resultat.isBeforeFirst())
            {
                while(resultat.next())
            {
                Employee e=new Employee();
                e.setId(resultat.getInt("id"));
                e.setNom(resultat.getString("nom"));
                e.setPrenom(resultat.getString("prenom"));
                e.setEmail(resultat.getString("email"));
                e.setCin(resultat.getString("cin"));
                e.setPhoto(resultat.getString("photo"));
                e.setMot_de_passe(resultat.getString("mot_de_passe"));
                e.setService_id(resultat.getInt("service_id"));
                e.setEquipe_id(resultat.getInt("equipe_id"));
                emps.add(e);
            }
            }
            else 
                emps= null;
            
        }
        catch(SQLException ex)
        {
            return null;
        }
        
        return emps;
    }
    
}
