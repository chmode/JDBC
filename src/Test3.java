import entites.Personne;
import services.IService;
import services.ServicePersonne;
import services.ServicePersonneFile;
import utils.Database;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test3 {
    
    public static void main(String[] args) {
        
        Personne p1 = new Personne("Mhmaed", "Jamali' OR 1=1;--", 10);
        Personne p2 = new Personne("Bakouri", "Salim", 17);
        
       // "SELECT * FROM users WHERE user='"+u+"' AND password='Jamali' OR 1=1;'"
        
        String url = "jdbc:mysql://localhost:3306/test_jdbc";
        String login = "root";
        String pwd = "";
       
        try {
        	Connection con=DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion etablie");
            
            try {
    			Statement ste = con.createStatement();
    			String requeteInsert = "INSERT INTO `personne` (`id`, `nom`, `prenom`, `age`) VALUES (NULL, '" + p1.getNom() + "', '" + p1.getPrenom() + "', '" + p1.getAge() + "');";
    	        ste.executeUpdate(requeteInsert);
    	        ste.close();
    	        //con.close();
    	        
    	        PreparedStatement pre=con.prepareStatement("INSERT INTO `personne` ( `nom`, `prenom`, `age`) VALUES ( ?, ?, ?);");
    	        pre.setString(1, p2.getNom());
    	        pre.setString(2, p2.getPrenom());
    	        pre.setInt(3, p2.getAge());
    	        pre.executeUpdate();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}