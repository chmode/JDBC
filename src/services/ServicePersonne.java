package services;

import entites.Personne;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import utils.Database;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicePersonne implements IService<Personne> {

    private Connection con;
    private Statement ste;

    public ServicePersonne() {
        con = Database.getInstance().getConnection();

    }

    @Override
    public void ajouter(Personne t)  {
        try {
			ste = con.createStatement();
			String requeteInsert = "INSERT INTO `personne` (`id`, `nom`, `prenom`, `age`) VALUES (NULL, '" + t.getNom() + "', '" + t.getPrenom() + "', '" + t.getAge() + "');";
	        ste.executeUpdate(requeteInsert);
	        ste.close();
	        //con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    public void ajouter1(Personne p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `personne` ( `nom`, `prenom`, `age`) VALUES ( ?, ?, ?);");
    pre.setString(1, p.getNom());
    pre.setString(2, p.getPrenom());
    pre.setInt(3, p.getAge());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Personne t) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); 
    	 PreparedStatement pre=con.prepareStatement("DELETE FROM `personne` WHERE nom =?;");
    	    pre.setString(1, t.getNom());
    	    pre.executeUpdate();
			return true;
    	   
    }

    @Override
    public boolean update(Personne t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Personne> readAll() {
    List<Personne> arr=new ArrayList<>();
    try {
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from personne");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               int age=rs.getInt("age");
               Personne p=new Personne(id, nom, prenom, age);
     arr.add(p);
     }
    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return arr;
    }
}
