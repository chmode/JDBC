import entites.Personne;
import services.IService;
import services.ServicePersonne;
import services.ServicePersonneFile;
import utils.Database;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test2 {
    
    public static void main(String[] args) {
    	IService<Personne> ser ;
    	String base = "db";
    	if(base.equals("file")) {
    	ser = new ServicePersonneFile();
    	}
    	else {
    		ser = new ServicePersonne();
    	}

        //ServicePersonne ser = new ServicePersonne();
        
        Personne p1 = new Personne("kamali", "Jamali", 10);
        Personne p2 = new Personne("Bakour'i", "Salim", 10);
        
        try {      
            //ser.ajouter1(p2);
        	ser.ajouter(p2);
            ser.ajouter(p1);
            List<Personne> list = ser.readAll();
            
            System.out.println(list);
            } catch (Exception ex) {      
            	System.out.println(ex);    
            }
    }
    
}