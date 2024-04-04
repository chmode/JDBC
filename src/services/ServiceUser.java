package services;

import entites.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Database;

public class ServiceUser {
       private Connection con = Database.getInstance().getConnection();

	// add user :
    public void ajouteruser(User u) {
        Statement st;
        try {
            st = con.createStatement();
            String req = "insert into user values ('"+ u.getId() + "','"+ u.getCin() + "','" + u.getNom()+ "','" + u.getPrenom()+ "','" + u.getEmail()+ "' )";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

	// afficher user : 
     public void afficherUser() {
        PreparedStatement pt;
        try {

            pt = con.prepareStatement("select * from user");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("User [id : " + rs.getInt(1) + ",cin du user : " + rs.getString(2) + ",nom du user : " + rs.getString(3) + ",prenom du user:" + rs.getString(4)+",email du user"+rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      public void modifierUser(int id, String nom) {
        try {
            PreparedStatement pt = con.prepareStatement("update user set nom=? where id=?");
            pt.setString(1, nom);
            pt.setInt(2, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
	// supprimer user : 
    public void supprimerUser(User u) {
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("delete from user where id=?");
            System.out.println("User id "+u.getId());
            pt.setInt(1, u.getId());
            pt.executeUpdate();
            System.out.println("Suppression avec succès");


        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
 /*  Recherche par nom  / Search by name   */
    public User chercherUserName(String nom)
     {
          User user=new User();
        String sqlnom = "SELECT * FROM user WHERE nom='"+nom+"'";
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(sqlnom);
            ResultSet result = statement.executeQuery(sqlnom); 
            while (result.next())
            {
                  
                  user.setId(result.getInt(1));
                  user.setCin(result.getString(2));
                  user.setNom(result.getString(3));
                  user.setPrenom(result.getString(4));
                  user.setEmail(result.getString(5));
                  
                  System.out.println("RECHERCHE PAR NOM SUCCESSFULLY WORKING");
                 
                  System.out.println("User [id : " + result.getInt(1) + ",cin du user : " + result.getString(2) + ",nom du user : " + result.getString(3) + ",prenom du user:" + result.getString(4)+",email du user" +result.getString(5));
                
            }
       

        } catch (SQLException ex) { 
        	ex.printStackTrace();
        }
        return user;
     }
    
     
    
    /*  Recherche par id  / Search by id*/
     public User chercherUserID(int id)
     {
          User c =new User();
        String sql = "SELECT * FROM user WHERE id='"+ id +"'";
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(sql);
            ResultSet reslt = statement.executeQuery(sql); 
            while (reslt.next())
            {
                
                    c.setId(reslt.getInt(1));
                  c.setCin(reslt.getString(2));
                  c.setNom(reslt.getString(3));
                  c.setPrenom(reslt.getString(4));
                  c.setEmail(reslt.getString(5));
                  
                  
                   System.out.println("RECHERCHE PAR IDDDDDD SUCCESSFULLY WORKING");
             
                 System.out.println("User [id : " + reslt.getInt(1) + ",cin du user : " + reslt.getString(2) + ",nom du user : " + reslt.getString(3) + ",prenom du user:" + reslt.getString(4)+",email du user" +reslt.getString(5));
            }
        
        } catch (SQLException e) { e.printStackTrace(); }
        return c;
      }
    }